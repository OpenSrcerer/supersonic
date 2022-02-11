package personal.opensrcerer.launch;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import personal.opensrcerer.aspect.SubscriberInitializer;
import personal.opensrcerer.aspect.annotations.Phase;

import javax.security.auth.login.LoginException;
import java.util.stream.Collectors;

public abstract class SupersonicLaunchWrapper {
    public static void run() throws LoginException {
        SubscriberInitializer.scan();

        SupersonicConstants.ENVIRONMENT_VARIABLES = System.getenv();

        SupersonicConstants.JDA = JDABuilder.create(
                SupersonicConstants.getVariable("DISCORD_TOKEN"),
                SupersonicConstants.cacheFlags
                        .stream()
                        .map(CacheFlag::getRequiredIntent)
                        .collect(Collectors.toSet()))
                .disableCache(SupersonicConstants.disabledCacheFlags)
                .enableCache(SupersonicConstants.cacheFlags)
                .setMemberCachePolicy(MemberCachePolicy.VOICE)
                .setEnableShutdownHook(true)
                .setActivity(
                        Activity.of(
                                SupersonicConstants.activity.getLeft(),
                                SupersonicConstants.activity.getRight()
                        )
                ).build();

        SubscriberInitializer.initializeSubscribers(Phase.PRE_READY);
    }
}
