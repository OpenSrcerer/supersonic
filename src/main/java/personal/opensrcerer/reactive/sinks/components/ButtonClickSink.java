package personal.opensrcerer.reactive.sinks.components;

import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import personal.opensrcerer.reactive.sinks.Sink;
import personal.opensrcerer.services.pagination.PaginationService;

public class ButtonClickSink implements Sink<ButtonClickEvent> {
    @Override
    @SuppressWarnings("ConstantConditions")
    public void onEvent(ButtonClickEvent event) {
        PaginationService.decode(event.getUser().getId(), event.getMessageId(), event.getButton().getId());
        event.deferEdit().queue();
    }
}
