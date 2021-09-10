package personal.opensrcerer.reactive.emitters.slash.player;

import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.emitters.slash.SlashCommandEmitter;
import personal.opensrcerer.reactive.sinks.slash.player.PauseSink;

public class PauseEmitter extends SlashCommandEmitter {
    public PauseEmitter() {
        super(
                SlashCommand.PAUSE.getName(),
                new PauseSink()
        );
    }
}
