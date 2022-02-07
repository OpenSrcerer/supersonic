package personal.opensrcerer.aspect;

import net.dv8tion.jda.api.Permission;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import personal.opensrcerer.aspect.annotations.AuthorizedBy;
import personal.opensrcerer.aspect.annotations.MappingStrategy;
import personal.opensrcerer.aspect.annotations.PostDuplex;
import personal.opensrcerer.aspect.annotations.PreDuplex;
import personal.opensrcerer.duplex.abstractions.DefaultDuplex;
import personal.opensrcerer.duplex.abstractions.GenericDuplex;
import personal.opensrcerer.duplex.payloads.EventMappingStrategy;

import javax.annotation.CheckForNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class DuplexInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DuplexInitializer.class);

    private static Set<Class<?>> preDuplexes = Collections.emptySet();
    private static Set<Class<?>> postDuplexes = Collections.emptySet();

    public static void scan() {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackage("personal.opensrcerer.duplex")
                        .setScanners(Scanners.TypesAnnotated)
        );

        preDuplexes = reflections.getTypesAnnotatedWith(PreDuplex.class);
        postDuplexes = reflections.getTypesAnnotatedWith(PostDuplex.class);
    }

    public static void initializePreDuplexes() {
        initializeDuplexes(preDuplexes);
    }

    public static void initializePostDuplexes() {
        initializeDuplexes(postDuplexes);
    }

    private static void initializeDuplexes(Set<Class<?>> duplexes) {
        duplexes.forEach(duplex -> Arrays.stream(duplex.getDeclaredConstructors())
                .filter(con -> con.getGenericParameterTypes().length == 0)
                .findFirst()
                .ifPresent(con -> {
                    con.setAccessible(true);
                    try {
                        DefaultDuplex<?> duplexInstance = (DefaultDuplex<?>) con.newInstance();
                        Permission[] permissions = secureDuplex(duplexInstance);
                        String mappingStrategyName = null;

                        if (duplexInstance instanceof GenericDuplex<?, ?> genericDuplex) {
                            mappingStrategyName = setDuplexMappingStrategy(genericDuplex);
                            if (mappingStrategyName == null) {
                                logger.error(duplex.getSimpleName() + " - Missing mapping strategy, cannot init.");
                                return;
                            }
                        }

                        duplexInstance.emit();
                        logInitialization(duplex.getSimpleName(), permissions, mappingStrategyName);
                    } catch (Exception ex) {
                        logger.error("Unhandled issue initializing duplexes:", ex);
                    }
                })
        );
    }

    @CheckForNull
    private static Permission[] secureDuplex(DefaultDuplex<?> duplex) {
        Class<?> duplexType = duplex.getClass();

        if (duplexType.getAnnotation(AuthorizedBy.class) == null) {
            return null;
        }

        Permission[] requiredPermissions = duplexType
                .getAnnotation(AuthorizedBy.class)
                .value();
        duplex.setRequiredPermissions(requiredPermissions);

        return requiredPermissions;
    }

    @CheckForNull
    private static String setDuplexMappingStrategy(GenericDuplex<?, ?> duplex) {
        Class<?> duplexType = duplex.getClass();

        if (duplexType.getAnnotation(MappingStrategy.class) == null) {
            return null;
        }

        EventMappingStrategy mappingStrategy = duplexType
                .getAnnotation(MappingStrategy.class)
                .value();
        duplex.setStrategy(mappingStrategy.get());

        return mappingStrategy.name();
    }

    private static void logInitialization(
            String duplexName,
            Permission[] permissions,
            String mappingStrategyName
    ) {
        String output = duplexName +
                " - Required permissions: " +
                ((permissions != null) ? Arrays.toString(permissions) : "none") +
                ". Mapping strategy: " +
                ((mappingStrategyName != null) ? mappingStrategyName : "none.");
        logger.info(output);
    }
}
