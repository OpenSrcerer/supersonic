package personal.opensrcerer.reactive.sinks.slash;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class MumSink extends SlashCommandSink {

    public MumSink(Permission... permissions) {
        super(permissions);
    }

    @Override
    public void receive(SlashCommandEvent event) {
        String reply = (event.getUser().getId().equals("178603029115830282")) ? "Yes." : "No.";
        event.reply(reply).queue();
    }
}
