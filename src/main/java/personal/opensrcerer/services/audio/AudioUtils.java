package personal.opensrcerer.services.audio;

import net.dv8tion.jda.api.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;
import personal.opensrcerer.messaging.constant.ConstantEmbeds;
import personal.opensrcerer.misc.PlayAdapter;
import personal.opensrcerer.requests.RequestUtils;
import personal.opensrcerer.requests.media.StreamRequest;
import personal.opensrcerer.responses.entities.Song;

public class AudioUtils {

    public static void playSongById(GenericInteractionCreateEvent event, Song song) {
        playSongById(event, song.getTitle(), song.id());
    }

    public static void playSongById(GenericInteractionCreateEvent event, String songTitle, String songId) {
        AudioManager manager = event.getGuild().getAudioManager();
        if (!manager.isConnected()) {
            manager.openAudioConnection(event.getMember().getVoiceState().getChannel());
            manager.setConnectionListener(new PlayAdapter() {
                @Override
                public void onStatusChange(@NotNull ConnectionStatus status) {
                    if (status.equals(ConnectionStatus.CONNECTED)) {
                        addToQueue(event, songId);
                    }
                }
            });
        } else {
            addToQueue(event, songId);
        }
        event.replyEmbeds(ConstantEmbeds.Companion.addedToQueue(songTitle)).setEphemeral(true).queue();
    }

    private static void addToQueue(GenericInteractionCreateEvent event, String trackId) {
        MusicPlayer.MUSIC_PLAYER.loadAndPlay(
                event.getTextChannel(),
                RequestUtils.INSTANCE.getUrl(
                        new StreamRequest(trackId, "128"),
                        event.getGuild().getId()
                ).toString()
        );
    }
}
