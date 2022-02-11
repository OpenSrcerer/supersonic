package personal.opensrcerer.reactive.subscribers.impl.components;

import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import personal.opensrcerer.aspect.annotations.Subscriber;
import personal.opensrcerer.aspect.mapping.EventMappingStrategy;
import personal.opensrcerer.reactive.payloads.impl.components.ClickEvent;
import personal.opensrcerer.reactive.subscribers.abstractions.GenericSuperscriber;
import personal.opensrcerer.services.pagination.PaginationService;

@Subscriber(typeToHandle = ButtonClickEvent.class,
        strategy = EventMappingStrategy.BUTTONCLICK_TO_CLICK)
public class ButtonClickscriber extends GenericSuperscriber<ButtonClickEvent, ClickEvent> {
    @Override
    public void onEvent(ClickEvent boxed) {
        ButtonClickEvent event = boxed.raw();
        if (event.getButton() == null && event.getButton().getId() == null) {
            return;
        }
        PaginationService.decode(event);
    }
}
