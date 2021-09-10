package personal.opensrcerer.reactive.emitters.slash.player;

import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.emitters.slash.SlashCommandEmitter;
import personal.opensrcerer.reactive.sinks.slash.player.SkipSink;

public class SkipEmitter extends SlashCommandEmitter {

    public SkipEmitter() {
        super(
                SlashCommand.SKIP.getName(),
                new SkipSink()
        );
    }
}
