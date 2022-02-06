package personal.opensrcerer.aspect.annotations;

import personal.opensrcerer.duplex.payloads.interfaces.EventMappingStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provide a Mapping Strategy for Generic Duplexes.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MappingStrategy {
    EventMappingStrategy value();
}
