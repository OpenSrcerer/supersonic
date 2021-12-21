package personal.opensrcerer.duplex.impl.slash.system;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.aspect.PostDuplex;
import personal.opensrcerer.client.SubsonicClient;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.duplex.abstractions.SlashCommandDuplex;
import personal.opensrcerer.requests.system.Ping;

@PostDuplex
public class PingDuplex extends SlashCommandDuplex {
    public PingDuplex() {
        super(SlashCommand.PING.getName());
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onEvent(SlashCommandEvent event) {
        SubsonicClient.INSTANCE.request(
                    new Ping(),
                    event.getGuild().getId()
            )
            .subscribe(res -> event.reply(
                "Pong!\nSubsonic Server Version: **" + res.getVersion() + "**\n"
                        + "Status: **" + res.getStatus() + "**\n"
                        + "Request took: **" + res.getTime() + "ms**\n").queue()
            );
    }
}
