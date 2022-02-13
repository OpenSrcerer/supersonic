package personal.opensrcerer.reactive.payloads.impl.components;

import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import personal.opensrcerer.reactive.payloads.Maybe;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicEvent;

public class ClickEvent extends SupersonicEvent<ButtonClickEvent> {
    public ClickEvent(ButtonClickEvent event) {
        super(event);
    }

    @Override
    public Maybe<?> evaluate() {
        return new Maybe<>(null);
    }
}
