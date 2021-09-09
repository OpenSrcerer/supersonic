package personal.opensrcerer.reactive.sinks.slash;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class LeaveVoiceSink extends SlashCommandSink {

    public LeaveVoiceSink(Permission... permissions) {
        super(permissions);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void receive(SlashCommandEvent event) {
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

        manager.closeAudioConnection();
        event.reply("Bye bye!").queue();
    }
}
