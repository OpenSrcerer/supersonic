package personal.opensrcerer.aspect.annotations;

import personal.opensrcerer.config.SlashCommand;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BoundTo {
    SlashCommand value();
}
