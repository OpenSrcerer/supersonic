package fluxTests.mockEvents;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.util.concurrent.atomic.AtomicInteger;

public class SlashCommandBlahEvent extends SlashCommandEvent {
    private static final AtomicInteger id = new AtomicInteger(0);

    public SlashCommandBlahEvent() {
        super(null, id.incrementAndGet(), null);
    }
}
