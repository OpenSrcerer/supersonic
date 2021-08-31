package personal.opensrcerer.launch;

import net.dv8tion.jda.api.JDABuilder;
import personal.opensrcerer.listeners.ListenerGroup;
import personal.opensrcerer.reactive.messageFluxes.ButtonClickEventFlux;
import personal.opensrcerer.reactive.messageFluxes.GuildMessageReceivedFlux;

import javax.security.auth.login.LoginException;

public abstract class ReactorApplicationLaunchWrapper {
    public static void run() throws LoginException {
        ReactorApplicationRuntimeConstants.ENVIRONMENT_VARIABLES = System.getenv();
        ReactorApplicationRuntimeConstants.JDA = JDABuilder.createDefault(
                ReactorApplicationRuntimeConstants.getVariable("DISCORD_TOKEN")
        ).build();
        ListenerGroup.addListeners(
                new GuildMessageReceivedFlux(),
                new ButtonClickEventFlux()
        );
    }
}
