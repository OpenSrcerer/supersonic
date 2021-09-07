package personal.opensrcerer.reactive.emitters;

import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import personal.opensrcerer.reactive.DiscordEventEmitter;
import personal.opensrcerer.handlers.components.ButtonClickSink;

public class ButtonClickEmitter extends DiscordEventEmitter<ButtonClickEvent> {

    private final ButtonClickSink handler;

    public ButtonClickEmitter() {
        super(ButtonClickEvent.class);
        this.handler = new ButtonClickSink();
    }

    @Override
    public void emit() {
        super.flux().subscribe(handler::receive);
    }
}
