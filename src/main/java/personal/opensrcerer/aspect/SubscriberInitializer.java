package personal.opensrcerer.aspect;

import net.dv8tion.jda.api.Permission;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import personal.opensrcerer.aspect.annotations.BoundTo;
import personal.opensrcerer.aspect.annotations.Phase;
import personal.opensrcerer.aspect.annotations.Subscriber;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.aspect.mapping.EventMappingStrategy;
import personal.opensrcerer.reactive.subscribers.abstractions.Authorizable;
import personal.opensrcerer.reactive.subscribers.abstractions.GenericSuperscriber;
import personal.opensrcerer.reactive.subscribers.abstractions.SlashCommandSuperscriber;

import javax.annotation.CheckForNull;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;


public class SubscriberInitializer {
    private static final Logger logger = LoggerFactory.getLogger(SubscriberInitializer.class);
    private static final StringBuilder outputBuffer = new StringBuilder();

    private static Set<Class<?>> subscribers = Collections.emptySet();

    public static void scan() {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackage("personal.opensrcerer.reactive.subscribers.impl")
                        .setScanners(Scanners.TypesAnnotated)
        );

        subscribers = reflections.getTypesAnnotatedWith(Subscriber.class);
    }

    public static void initializeSubscribers(Phase phase) {
        outputBuffer.setLength(0);
        bufferPhase(phase);
        subscribers.stream()
                .filter(subClazz -> subClazz.getAnnotation(Subscriber.class).phase().equals(phase))
                .forEach(subClazz -> Arrays.stream(subClazz.getDeclaredConstructors())
                    .filter(con -> con.getGenericParameterTypes().length == 0)
                    .findFirst()
                    .ifPresentOrElse(con -> {
                        con.setAccessible(true);
                        constructSubscriber(subClazz, con);
                    }, () -> {
                        throw new NullPointerException("Zero-arg constructor not provided for subscriber:");
                    })
        );
        outputBuffer.append("*****************************************\n");
        logger.info(outputBuffer.toString());
    }

    private static void constructSubscriber(Class<?> subscriber, Constructor<?> con) {
        try {
            GenericSuperscriber<?, ?> superscriber = (GenericSuperscriber<?, ?>) con.newInstance();
            Pair<String, String> strategyTypePair = setTranslation(superscriber);
            String slashCommandName = null;
            Permission[] permissions = null;

            if (superscriber instanceof SlashCommandSuperscriber<?> scSuperscriber) {
                slashCommandName = setSlashCommand(scSuperscriber);
            }

            if (superscriber instanceof Authorizable authorizable) {
                permissions = secureSubscriber(authorizable);
            }

            superscriber.subscribe();
            bufferInitialization(subscriber.getSimpleName(), permissions, strategyTypePair.getLeft(),
                    strategyTypePair.getRight(), slashCommandName);
        } catch (Exception ex) {
            logger.error("Unhandled exception while initializing subscribers:", ex);
        }
    }

    @NotNull
    private static Pair<String, String> setTranslation(GenericSuperscriber<?, ?> subscriber) {
        Class<?> subscriberType = subscriber.getClass();
        Subscriber subAnno = subscriberType.getAnnotation(Subscriber.class);
        Class<?> typeToHandle = subAnno.typeToHandle();
        EventMappingStrategy strategy = subAnno.strategy();
        subscriber.setTranslation(typeToHandle, strategy.get());
        return Pair.of(strategy.name(), typeToHandle.getSimpleName());
    }

    @CheckForNull
    private static Permission[] secureSubscriber(Authorizable<?> subscriber) {
        Class<?> subscriberType = subscriber.getClass();
        Permission[] requiredPermissions = subscriberType.getAnnotation(Subscriber.class).permissions();
        subscriber.setRequiredPermissions(requiredPermissions);
        return requiredPermissions;
    }

    @CheckForNull
    private static String setSlashCommand(SlashCommandSuperscriber<?> subscriber) {
        Class<?> subscriberType = subscriber.getClass();
        SlashCommand command = subscriberType.getAnnotation(BoundTo.class).value();
        subscriber.setCommand(command);
        return command.getName();
    }

    private static void bufferPhase(Phase phase) {
        outputBuffer.append("\n*************** ")
                .append(phase.name())
                .append(" ***************\n");
    }

    private static void bufferInitialization(
            String subscriberName,
            Permission[] permissions,
            String mappingStrategyName,
            String typeToHandleName,
            String slashCommandName
    ) {
        String output = subscriberName +
                " - Required permissions: " +
                ((permissions != null) ? Arrays.toString(permissions) : "none") +
                ". Mapping strategy: " + mappingStrategyName + ". Event type: " +
                typeToHandleName + ". Command name: " +
                ((slashCommandName != null) ? slashCommandName + "\n" : "none.\n");
        outputBuffer.append(output);
    }
}
