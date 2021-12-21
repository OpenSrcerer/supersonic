package personal.opensrcerer.aspect;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import personal.opensrcerer.duplex.interfaces.Emitter;

import java.util.Arrays;
import java.util.Set;

public class DuplexInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DuplexInitializer.class);

    private static final Set<Class<?>> preDuplexes;
    private static final Set<Class<?>> postDuplexes;

    static {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackage("personal.opensrcerer.duplex")
                        .setScanners(Scanners.TypesAnnotated)
        );

        preDuplexes = reflections.getTypesAnnotatedWith(PreDuplex.class);
        postDuplexes = reflections.getTypesAnnotatedWith(PostDuplex.class);
    }

    public synchronized static void initializePreDuplexes() {
        initializeDuplexes(preDuplexes);
    }

    public synchronized static void initializePostDuplexes() {
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
                    } catch (Exception ex) {
                        logger.error("Issue initializing duplexes:", ex);
                    }
                })
        );
    }
}
