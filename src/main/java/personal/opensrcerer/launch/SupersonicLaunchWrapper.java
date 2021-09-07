package personal.opensrcerer.launch;

import net.dv8tion.jda.api.JDABuilder;
import personal.opensrcerer.reactive.Emitter;
import personal.opensrcerer.reactive.emitters.ButtonClickEmitter;
import personal.opensrcerer.reactive.emitters.GuildMessageReceivedEmitter;

import javax.security.auth.login.LoginException;

public abstract class SupersonicLaunchWrapper {
    public static void run() throws LoginException {
        SupersonicRuntimeConstants.ENVIRONMENT_VARIABLES = System.getenv();
        SupersonicRuntimeConstants.JDA = JDABuilder.createDefault(
                SupersonicRuntimeConstants.getVariable("DISCORD_TOKEN")
        ).build();
        Emitter.addListeners(
                new GuildMessageReceivedEmitter(),
                new ButtonClickEmitter()
        );
    }
}
