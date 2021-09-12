package personal.opensrcerer.reactive.emitters.slash.player;

import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.emitters.slash.SlashEmitter;
import personal.opensrcerer.reactive.sinks.slash.player.SearchSink;

public class SearchEmitter extends SlashEmitter {
    public SearchEmitter() {
        super(
                SlashCommand.SEARCH.getName(),
                new SearchSink()
        );
    }
}
