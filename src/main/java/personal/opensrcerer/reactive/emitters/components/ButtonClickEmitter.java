package personal.opensrcerer.reactive.emitters.components;

import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import personal.opensrcerer.reactive.emitters.DiscordEmitter;
import personal.opensrcerer.reactive.sinks.components.ButtonClickSink;

public class ButtonClickEmitter extends DiscordEmitter<ButtonClickEvent> {

    public ButtonClickEmitter() {
        super(
                ButtonClickEvent.class,
                new ButtonClickSink()
        );
    }
}
