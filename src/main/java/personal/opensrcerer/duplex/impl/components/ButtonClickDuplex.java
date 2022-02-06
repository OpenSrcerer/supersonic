package personal.opensrcerer.duplex.impl.components;

import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import personal.opensrcerer.aspect.annotations.PostDuplex;
import personal.opensrcerer.duplex.abstractions.DefaultDuplex;
import personal.opensrcerer.services.pagination.PaginationService;

@PostDuplex
public class ButtonClickDuplex extends DefaultDuplex<ButtonClickEvent> {
    public ButtonClickDuplex() {
        super(ButtonClickEvent.class);
    }

    @Override
    public void onEvent(ButtonClickEvent event) {
        PaginationService.decode(event);
    }

    @Override
    public boolean isValid(ButtonClickEvent buttonClickEvent) {
        return buttonClickEvent.getButton() != null && buttonClickEvent.getButton().getId() != null;
    }
}
