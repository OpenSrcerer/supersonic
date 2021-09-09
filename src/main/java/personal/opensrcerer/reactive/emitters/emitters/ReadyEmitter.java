package personal.opensrcerer.reactive.emitters.emitters;

import net.dv8tion.jda.api.events.ReadyEvent;
import personal.opensrcerer.reactive.sinks.system.ReadySink;
import personal.opensrcerer.reactive.emitters.DiscordEventEmitter;

public class ReadyEmitter extends DiscordEventEmitter<ReadyEvent> {

    private final ReadySink sink;

    public ReadyEmitter() {
        super(ReadyEvent.class);
        this.sink = new ReadySink();
    }

    @Override
    public void emit() {
        super.flux().subscribe(sink::receive);
    }
}
