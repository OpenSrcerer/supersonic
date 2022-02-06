package personal.opensrcerer.duplex.impl.slash.player;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import personal.opensrcerer.aspect.annotations.AuthorizedBy;
import personal.opensrcerer.aspect.annotations.PostDuplex;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.duplex.abstractions.DefaultSlashCommandDuplex;

@PostDuplex
@AuthorizedBy({
        Permission.MESSAGE_WRITE,
        Permission.VIEW_CHANNEL,
        Permission.VOICE_CONNECT,
        Permission.VOICE_SPEAK
})
public class JoinDuplex extends DefaultSlashCommandDuplex {
    public JoinDuplex() {
        super(SlashCommand.JOIN.getName());
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onEvent(SlashCommandEvent event) {
        GuildVoiceState state = event.getMember().getVoiceState();
        AudioManager manager = event.getGuild().getAudioManager();
        boolean botConnected = manager.isConnected();
        boolean memberConnected = state.inVoiceChannel();

        if (!memberConnected) {
            event.reply("You are not in a voice channel!").queue();
            return;
        }

        VoiceChannel memberChannel = state.getChannel();
        if (botConnected && !manager.getConnectedChannel().getId().equals(memberChannel.getId())) {
            manager.openAudioConnection(memberChannel);
            event.reply("Switched channels to: <#" + memberChannel.getId() + ">").queue();
            return;
        }

        if (botConnected && manager.getConnectedChannel().getId().equals(memberChannel.getId())) {
            event.reply("The bot is already in <#" + memberChannel.getId() + ">").queue();
            return;
        }

        manager.openAudioConnection(memberChannel);
        event.reply("Joined <#" + memberChannel.getId() + ">").queue();
    }
}
