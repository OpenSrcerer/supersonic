package personal.opensrcerer.reactive.emitters.slash.player;

import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.emitters.slash.SlashEmitter;
import personal.opensrcerer.reactive.sinks.slash.player.UnpauseSink;

public class UnpauseEmitter extends SlashEmitter {
    public UnpauseEmitter() {
        super(
                SlashCommand.UNPAUSE.getName(),
                new UnpauseSink()
        );
    }
}
