package personal.opensrcerer.reactive.emitters.slash.player;

import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.emitters.slash.SlashEmitter;
import personal.opensrcerer.reactive.sinks.slash.player.SkipSink;

public class SkipEmitter extends SlashEmitter {
    public SkipEmitter() {
        super(
                SlashCommand.SKIP.getName(),
                new SkipSink()
        );
    }
}
