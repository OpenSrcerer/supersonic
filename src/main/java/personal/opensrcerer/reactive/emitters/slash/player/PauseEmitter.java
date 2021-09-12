package personal.opensrcerer.reactive.emitters.slash.player;

import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.emitters.slash.SlashEmitter;
import personal.opensrcerer.reactive.sinks.slash.player.PauseSink;

public class PauseEmitter extends SlashEmitter {
    public PauseEmitter() {
        super(
                SlashCommand.PAUSE.getName(),
                new PauseSink()
        );
    }
}
