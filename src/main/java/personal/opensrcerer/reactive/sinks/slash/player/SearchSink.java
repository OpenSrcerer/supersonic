package personal.opensrcerer.reactive.sinks.slash.player;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import personal.opensrcerer.client.SubsonicClient;
import personal.opensrcerer.messaging.paginatedEmbeds.search.SearchEmbed;
import personal.opensrcerer.messaging.paginatedEmbeds.search.SearchEmbedType;
import personal.opensrcerer.reactive.sinks.slash.SlashCommandSink;
import personal.opensrcerer.requests.search.Search3;

import java.util.Map;

public class SearchSink extends SlashCommandSink {
    public SearchSink(Permission... permissions) {
        super(permissions);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onEvent(SlashCommandEvent event) {
        OptionMapping o = event.getOption("query");

        if (o == null) {
            event.reply("Missing query parameter!").queue();
            return;
        }

        var results = SubsonicClient.INSTANCE.request(
                new Search3(
                        Map.of(
                                "query", o.getAsString(),
                                "songCount", "10"
                        )
                ),
                event.getGuild().getId()
        );

        SearchEmbed embed = new SearchEmbed(results);

        event.replyEmbeds(
                embed.getEmbed(SearchEmbedType.SONG).current()
        ).queue();
    }
}
