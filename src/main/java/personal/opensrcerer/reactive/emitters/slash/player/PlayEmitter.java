package personal.opensrcerer.reactive.emitters.slash.player;

import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.emitters.slash.SlashCommandEmitter;
import personal.opensrcerer.reactive.sinks.slash.player.PlaySink;

public class PlayEmitter extends SlashCommandEmitter {
    public PlayEmitter() {
        super(
                SlashCommand.PLAY.getName(),
                new PlaySink()
        );
    }
}
