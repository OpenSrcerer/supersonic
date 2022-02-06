package personal.opensrcerer.duplex.impl.slash.player;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import personal.opensrcerer.aspect.annotations.AuthorizedBy;
import personal.opensrcerer.aspect.annotations.PostDuplex;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.duplex.abstractions.DefaultSlashCommandDuplex;
import personal.opensrcerer.services.audio.MusicPlayer;

@PostDuplex
@AuthorizedBy({
        Permission.MESSAGE_WRITE
})
public class LeaveDuplex extends DefaultSlashCommandDuplex {
    public LeaveDuplex() {
        super(SlashCommand.LEAVE.getName());
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onEvent(SlashCommandEvent event) {
        AudioManager manager = event.getGuild().getAudioManager();
        GuildVoiceState state = event.getMember().getVoiceState();
        boolean botConnected = manager.isConnected();
        boolean memberConnected = state.inVoiceChannel();

        if (!botConnected) {
            event.reply("I am not currently connected to a voice channel!").queue();
            return;
        }

        if (!memberConnected || memberConnected && !state.getChannel().getId().equals(manager.getConnectedChannel().getId())) {
            event.reply("Join <#" + manager.getConnectedChannel().getId() + "> to be able to control the bot!").queue();
            return;
        }

        MusicPlayer.MUSIC_PLAYER.stop(event.getGuild());
        manager.closeAudioConnection();
        event.reply("Bye bye!").queue();
    }
}
