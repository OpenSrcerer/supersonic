package personal.opensrcerer.reactive.emitters.system;

import net.dv8tion.jda.api.events.ReadyEvent;
import personal.opensrcerer.reactive.sinks.system.ReadySink;
import personal.opensrcerer.reactive.emitters.DiscordEventEmitter;

public class ReadyEmitter extends DiscordEventEmitter<ReadyEvent> {

    public ReadyEmitter() {
        super(
                ReadyEvent.class,
                new ReadySink()
        );
    }
}
