package personal.opensrcerer.reactive.emitters.slash.player;

import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.emitters.slash.SlashEmitter;
import personal.opensrcerer.reactive.sinks.slash.player.PlaySink;

public class PlayEmitter extends SlashEmitter {
    public PlayEmitter() {
        super(
                SlashCommand.PLAY.getName(),
                new PlaySink()
        );
    }
}
