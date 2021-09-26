package personal.opensrcerer.reactive.emitters.slash.system;

import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.emitters.slash.SlashEmitter;
import personal.opensrcerer.reactive.sinks.slash.system.PingSink;

public class PingEmitter extends SlashEmitter {
    public PingEmitter() {
        super(
                SlashCommand.PING.getName(),
                new PingSink()
        );
    }
}
