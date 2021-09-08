package personal.opensrcerer.reactive.emitters;

import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import personal.opensrcerer.reactive.DiscordEventEmitter;
import personal.opensrcerer.sinks.components.ButtonClickSink;

public class ButtonClickEmitter extends DiscordEventEmitter<ButtonClickEvent> {

    private final ButtonClickSink sink;

    public ButtonClickEmitter() {
        super(ButtonClickEvent.class);
        this.sink = new ButtonClickSink();
    }

    @Override
    public void emit() {
        super.flux().subscribe(sink::receive);
    }
}
