package personal.opensrcerer.aspect.annotations;

import net.dv8tion.jda.api.Permission;
import personal.opensrcerer.aspect.mapping.EventMappingStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Subscriber {
    Class<?> typeToHandle();

    EventMappingStrategy strategy();

    Permission[] permissions() default {};

    Phase phase() default Phase.POST_READY;
}
