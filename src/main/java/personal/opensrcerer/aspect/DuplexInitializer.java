package personal.opensrcerer.aspect;

import net.dv8tion.jda.api.Permission;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import personal.opensrcerer.duplex.abstractions.DiscordDuplex;

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
                        DiscordDuplex<?> duplexInstance = (DiscordDuplex<?>) con.newInstance();
                        Permission[] permissions = secureDuplex(duplexInstance);
                        duplexInstance.emit();
                        logInitialization(duplex.getSimpleName(), permissions);
                    } catch (Exception ex) {
                        logger.error("Issue initializing duplexes:", ex);
                    }
                })
        );
    }

    @CheckForNull
    private static Permission[] secureDuplex(DiscordDuplex<?> duplex) {
        Class<?> duplexType = duplex.getClass();

        if (duplexType.getAnnotation(AuthorizedBy.class) == null) {
            return null;
        }

        Permission[] requiredPermissions = duplexType
                .getAnnotation(AuthorizedBy.class)
                .requiredPermissions();
        duplex.setRequiredPermissions(requiredPermissions);

        return requiredPermissions;
    }

    private static void logInitialization(
            String duplexName,
            Permission[] permissions
    ) {
        StringBuilder output = new StringBuilder();
        if (permissions == null) {
            output.append(duplexName)
                    .append(" initialized with no Permission requirements.");
        } else {
            output.append(duplexName)
                    .append(" initialized. Will be secured with: ")
                    .append(Arrays.toString(permissions));
        }
        logger.info(output.toString());
    }
}
