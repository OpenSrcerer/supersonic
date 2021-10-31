package personal.opensrcerer.reactive.emitters.components;

import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import personal.opensrcerer.reactive.emitters.DiscordEmitter;
import personal.opensrcerer.reactive.sinks.components.ButtonClickSink;

public class ButtonClickEmitter extends DiscordEmitter<ButtonClickEvent> {

    public ButtonClickEmitter() {
        super(
                ButtonClickEvent.class,
                new ButtonClickSink()
        );
    }

    @Override
    public boolean filterValid(ButtonClickEvent buttonClickEvent) {
        return buttonClickEvent.getButton() != null && buttonClickEvent.getButton().getId() != null;
    }
}
