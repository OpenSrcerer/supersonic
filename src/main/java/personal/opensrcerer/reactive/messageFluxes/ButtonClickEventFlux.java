package personal.opensrcerer.reactive.messageFluxes;

import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import personal.opensrcerer.reactive.DiscordFlux;
import personal.opensrcerer.handlers.components.ButtonClickHandler;

public class ButtonClickEventFlux extends DiscordFlux<ButtonClickEvent> {

    private final ButtonClickHandler handler;

    public ButtonClickEventFlux() {
        super(ButtonClickEvent.class);
        this.handler = new ButtonClickHandler();
    }

    @Override
    public void listen() {
        super.flux().subscribe(handler::handle);
    }
}
