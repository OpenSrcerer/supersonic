package personal.opensrcerer.duplex.impl.slash.player;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import personal.opensrcerer.aspect.PostDuplex;
import personal.opensrcerer.client.SubsonicClient;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.duplex.abstractions.SlashCommandDuplex;
import personal.opensrcerer.messaging.impl.paginatedEmbeds.search.SearchEmbed;
import personal.opensrcerer.messaging.impl.paginatedEmbeds.search.SearchEmbedActionRowTemplates;
import personal.opensrcerer.requests.search.Search3;
import personal.opensrcerer.services.pagination.PaginationService;

import java.util.Map;

@PostDuplex
public class SearchDuplex extends SlashCommandDuplex {
    public SearchDuplex() {
        super(SlashCommand.SEARCH.getName());
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
                .subscribe(e -> event.replyEmbeds(e.current().asMessageEmbed(e.currentRow()))
                        .addActionRows(SearchEmbedActionRowTemplates.Companion.get(e))
                        .queue(hook -> hook.retrieveOriginal()
                                .queue(message -> PaginationService
                                        .add(message.getId(), e, hook)))
                );
    }
}
