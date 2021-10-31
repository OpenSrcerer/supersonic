package personal.opensrcerer.reactive.sinks.slash.player;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import personal.opensrcerer.client.SubsonicClient;
import personal.opensrcerer.messaging.implementations.paginatedEmbeds.search.SearchEmbed;
import personal.opensrcerer.messaging.implementations.paginatedEmbeds.search.SearchEmbedActionRowTemplates;
import personal.opensrcerer.reactive.sinks.slash.SlashCommandSink;
import personal.opensrcerer.requests.search.Search3;
import personal.opensrcerer.services.pagination.PaginationService;

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
                                "songCount", "100",
                                "albumCount", "100",
                                "artistCount", "100"
                        )
                ), event.getGuild().getId()
        );

        results.map(SearchEmbed::new)
                .subscribe(e -> event.replyEmbeds(e.current())
                            .addActionRows(SearchEmbedActionRowTemplates.Companion.get(e))
                            .queue(i -> i.retrieveOriginal()
                            .queue(m -> PaginationService
                            .add(m.getId(), e, i)))
                );
    }
}
