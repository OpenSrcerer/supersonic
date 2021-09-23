package personal.opensrcerer.reactive.sinks.slash.misc;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.client.SubsonicClient;
import personal.opensrcerer.reactive.sinks.slash.SlashCommandSink;
import personal.opensrcerer.requests.search.Search2;

import java.util.Arrays;
import java.util.Map;

public class MumSink extends SlashCommandSink {

    public MumSink(Permission... permissions) {
        super(permissions);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onEvent(SlashCommandEvent event) {
        String reply = (event.getUser().getId().equals("178603029115830282")) ? "Yes." : "No.";
        event.reply(reply).queue();

        var e = SubsonicClient.INSTANCE.request(
                new Search2(Map.of("query", "local h")),
                event.getGuild().getId()
        );

        System.out.println(Arrays.toString(e.getArtists()));
    }
}
