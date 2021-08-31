package personal.opensrcerer.launch;

import net.dv8tion.jda.api.JDA;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.Map;

public abstract class ReactorApplicationRuntimeConstants {
    protected static JDA JDA;
    protected static Map<String, String> ENVIRONMENT_VARIABLES;

    @Nonnull
    @CheckReturnValue
    public static JDA getJDA() {
        return JDA;
    }

    @Nullable
    @CheckReturnValue
    @Contract(value = "null -> null", pure = true)
    public static String getVariable(String key) {
        if (key == null) {
            return null;
        }

        return ENVIRONMENT_VARIABLES.get(key);
    }

    @Nullable
    @CheckReturnValue
    @Contract(value = "null, _ -> null", pure = true)
    public static String getVariable(String key, String orElse) {
        if (key == null) {
            return null;
        }

        if (orElse == null) {
            return getVariable(key);
        }

        return ENVIRONMENT_VARIABLES.getOrDefault(key, orElse);
    }

    /**
     * Checks if an entry exists in ENVIRONMENT_VARIABLES for the given key.
     * @param key The key to use for the check.
     * @return False if value does not exist for given key or if given key is null.
     */
    @Contract(value = "null -> false", pure = true)
    public static boolean isDefined(String key) {
        if (key == null) {
            return false;
        }

        return ENVIRONMENT_VARIABLES.containsKey(key);
    }
}
