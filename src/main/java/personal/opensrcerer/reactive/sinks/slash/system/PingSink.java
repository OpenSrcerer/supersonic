package personal.opensrcerer.reactive.sinks.slash.system;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.client.SubsonicClient;
import personal.opensrcerer.reactive.sinks.slash.SlashCommandSink;
import personal.opensrcerer.requests.system.Ping;

public class PingSink extends SlashCommandSink {

    public PingSink(Permission... permissions) {
        super(permissions);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onEvent(SlashCommandEvent event) {
        SubsonicClient.INSTANCE.request(
                new Ping(),
                event.getGuild().getId()
        ).subscribe(r -> event.reply(
                "Pong!\nSubsonic Server Version: **" + r.getVersion() + "**\n"
                + "Status: **" + r.getStatus() + "**\n"
                + "Request took: **" + r.getTime() + "ms**\n"
        ).queue());
    }
}
