package personal.opensrcerer.duplex.impl.slash.misc;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.aspect.AuthorizedBy;
import personal.opensrcerer.aspect.PostDuplex;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.duplex.abstractions.SlashCommandDuplex;

@PostDuplex
@AuthorizedBy(requiredPermissions = {
        Permission.VIEW_CHANNEL,
        Permission.MESSAGE_WRITE,
        Permission.ADMINISTRATOR
})
public class MumDuplex extends SlashCommandDuplex {
    public MumDuplex() {
        super(SlashCommand.MUM.getName());
    }

    @Override
    public void onEvent(SlashCommandEvent event) {
        String reply = (event.getUser().getId().equals("178603029115830282")) ? "Yes." : "No.";
        event.reply(reply).queue();
    }
}
