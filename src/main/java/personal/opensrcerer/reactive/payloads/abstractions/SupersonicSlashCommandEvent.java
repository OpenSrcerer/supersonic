package personal.opensrcerer.reactive.payloads.abstractions;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyAction;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import java.util.Arrays;

public abstract class SupersonicSlashCommandEvent extends SupersonicEvent<SlashCommandEvent> {
    public SupersonicSlashCommandEvent(SlashCommandEvent event) {
        super(event);
    }

    @CheckReturnValue
    public ReplyAction replyEmbeds(MessageEmbed... embeds) {
        return rawEvent.replyEmbeds(Arrays.asList(embeds)).setEphemeral(true);
    }

    @CheckReturnValue
    public ReplyAction reply(String content, boolean ephemeral) {
        return rawEvent.reply(content).setEphemeral(ephemeral);
    }

    public Guild getGuild() {
        return rawEvent.getGuild();
    }

    public Member getMember() {
        return rawEvent.getMember();
    }

    public GuildVoiceState getMemberVoiceState() {
        return rawEvent.getMember().getVoiceState();
    }

    public AudioManager getAudioManager() {
        return rawEvent.getGuild().getAudioManager();
    }

    @Nullable
    public VoiceChannel getMemberVoiceChannel() {
        return getMemberVoiceState().getChannel();
    }

    @Nullable
    public VoiceChannel getConnectedVoiceChannel() {
        return getAudioManager().getConnectedChannel();
    }

    public boolean isBotConnected() {
        return getAudioManager().isConnected();
    }

    public boolean memberInVoice() {
        return getMemberVoiceState().inVoiceChannel();
    }
}
