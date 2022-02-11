package personal.opensrcerer.reactive.subscribers.impl.slash.misc;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.payloads.impl.slash.MumEvent;
import personal.opensrcerer.reactive.subscribers.abstractions.SlashCommandSuperscriber;

public class MumScriber extends SlashCommandSuperscriber<MumEvent> {
    @Override
    public void onEvent(MumEvent event) {
        SlashCommandEvent raw = event.raw();
        String reply = (raw.getUser().getId().equals("178603029115830282")) ? "Yes." : "No.";
        raw.reply(reply).queue();
    }
}
