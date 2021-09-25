package personal.opensrcerer.launch;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.internal.utils.tuple.Pair;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.Map;

public abstract class SupersonicConstants {
    public static final String BOT_ID = "881544361643507713";
    public static final Pair<Activity.ActivityType, String> activity = Pair.of(
            Activity.ActivityType.DEFAULT,
            "abcdefghijklmnopqrstuvxyz"
    );
    public static final EnumSet<CacheFlag> cacheFlags = EnumSet.of(
            CacheFlag.VOICE_STATE
    );
    public static final EnumSet<CacheFlag> disabledCacheFlags = EnumSet.of(
            CacheFlag.ACTIVITY,
            CacheFlag.EMOTE,
            CacheFlag.CLIENT_STATUS,
            CacheFlag.ONLINE_STATUS
    );
    public static final int DEFAULT_PAGE_SIZE = 8;

    protected static JDA JDA;
    protected static Map<String, String> ENVIRONMENT_VARIABLES;
    protected static AudioPlayerManager AUDIO_MANAGER;

    static {
        AUDIO_MANAGER = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(AUDIO_MANAGER);
    }

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
