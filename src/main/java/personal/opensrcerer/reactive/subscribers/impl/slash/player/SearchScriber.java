package personal.opensrcerer.reactive.subscribers.impl.slash.player;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.aspect.annotations.BoundTo;
import personal.opensrcerer.aspect.annotations.Subscriber;
import personal.opensrcerer.aspect.mapping.EventMappingStrategy;
import personal.opensrcerer.client.SubsonicService;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.messaging.impl.paginatedEmbeds.search.SearchEmbed;
import personal.opensrcerer.messaging.impl.paginatedEmbeds.search.SearchEmbedActionRowTemplates;
import personal.opensrcerer.reactive.payloads.Maybe;
import personal.opensrcerer.reactive.payloads.impl.slash.SearchEvent;
import personal.opensrcerer.reactive.subscribers.abstractions.SlashCommandSuperscriber;
import personal.opensrcerer.requests.search.Search3;
import personal.opensrcerer.services.pagination.PaginationService;

import java.util.Map;

@Subscriber(typeToHandle = SlashCommandEvent.class,
        strategy = EventMappingStrategy.SLASHCOMMAND_TO_SEARCH)
@BoundTo(SlashCommand.SEARCH)
public class SearchScriber extends SlashCommandSuperscriber<SearchEvent> {
    @Override
    public void onEvent(SearchEvent boxed) {
        Maybe<String> maybe = boxed.evaluate();
        if (!maybe.ok()) {
            boxed.replyEmbeds(maybe.err()).queue();
            return;
        }

        var results = SubsonicService.INSTANCE.request(new Search3(
                Map.of(
                        "query", maybe.value(),
                        "songCount", "100",
                        "albumCount", "100",
                        "artistCount", "100"
                )), boxed.getGuild().getId()
        );

        results.map(SearchEmbed::new)
                .subscribe(e -> boxed.replyEmbeds(e.current().asMessageEmbed(e.currentRow()))
                        .addActionRows(SearchEmbedActionRowTemplates.Companion.get(e))
                        .queue(hook -> hook.retrieveOriginal()
                        .queue(message -> PaginationService
                        .add(message.getId(), e, hook)))
                );
    }
}
