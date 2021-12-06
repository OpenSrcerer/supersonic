package personal.opensrcerer.reactive.sinks.slash.misc;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.sinks.slash.SlashCommandSink;

public class MumSink extends SlashCommandSink {

    public MumSink(Permission... permissions) {
        super(permissions);
    }

    @Override
    public void onEvent(SlashCommandEvent event) {
        String reply = (event.getUser().getId().equals("178603029115830282")) ? "Yes." : "No.";
        event.reply(reply).queue();
    }
}
