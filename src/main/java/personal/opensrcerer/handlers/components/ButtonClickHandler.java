package personal.opensrcerer.handlers.components;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import personal.opensrcerer.handlers.FluxEventHandler;

import java.util.ArrayList;
import java.util.List;

public class ButtonClickHandler implements FluxEventHandler<ButtonClickEvent, ButtonClickEvent> {
    private static final List<Role> pugroles = new ArrayList<>();

    @Override
    public void handle(ButtonClickEvent p) {

    }

    @Override
    public boolean isValid(ButtonClickEvent e) {
        return true;
    }
}
