package personal.opensrcerer.reactive.emitters.slash.player;

import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.emitters.slash.SlashCommandEmitter;
import personal.opensrcerer.reactive.sinks.slash.player.PauseSink;
import personal.opensrcerer.reactive.sinks.slash.player.UnpauseSink;

public class UnpauseEmitter extends SlashCommandEmitter {
    public UnpauseEmitter() {
        super(
                SlashCommand.UNPAUSE.getName(),
                new UnpauseSink()
        );
    }
}
