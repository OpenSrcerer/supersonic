package personal.opensrcerer.aspect;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import personal.opensrcerer.duplex.interfaces.Emitter;

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
        duplexes.forEach(
                duplex -> Arrays.stream(duplex.getDeclaredConstructors())
                .filter(con -> con.getGenericParameterTypes().length == 0)
                .findFirst()
                .ifPresent(con -> {
                    con.setAccessible(true);
                    try {
                        Emitter emitter = (Emitter) con.newInstance();
                        emitter.emit();
                        logger.info("Duplex up: " + duplex.getSimpleName());
                    } catch (Exception ex) {
                        logger.error("Issue initializing duplexes:", ex);
                    }
                })
        );
    }
}
