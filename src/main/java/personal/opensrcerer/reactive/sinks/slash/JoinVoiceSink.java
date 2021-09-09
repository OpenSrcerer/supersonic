package personal.opensrcerer.reactive.sinks.slash;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinVoiceSink extends SlashCommandSink {

    public JoinVoiceSink(Permission... permissions) {
        super(permissions);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void receive(SlashCommandEvent event) {
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
