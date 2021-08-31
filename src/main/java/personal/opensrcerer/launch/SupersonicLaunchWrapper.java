package personal.opensrcerer.launch;

import net.dv8tion.jda.api.JDABuilder;
import personal.opensrcerer.listeners.FluxListener;
import personal.opensrcerer.reactive.messageFluxes.ButtonClickEventFlux;
import personal.opensrcerer.reactive.messageFluxes.GuildMessageReceivedFlux;

import javax.security.auth.login.LoginException;

public abstract class SupersonicLaunchWrapper {
    public static void run() throws LoginException {
        SupersonicRuntimeConstants.ENVIRONMENT_VARIABLES = System.getenv();
        SupersonicRuntimeConstants.JDA = JDABuilder.createDefault(
                SupersonicRuntimeConstants.getVariable("DISCORD_TOKEN")
        ).build();
        FluxListener.addListeners(
                new GuildMessageReceivedFlux(),
                new ButtonClickEventFlux()
        );
    }
}
