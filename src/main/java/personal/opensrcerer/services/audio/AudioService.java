package personal.opensrcerer.services.audio;

import net.dv8tion.jda.api.audio.hooks.ConnectionListener;
import net.dv8tion.jda.api.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;
import personal.opensrcerer.messaging.entities.EmbedEntity;
import personal.opensrcerer.messaging.entities.Page;
import personal.opensrcerer.messaging.interfaces.embedInterfaces.PaginatedCursorized;
import personal.opensrcerer.requests.RequestFormatter;
import personal.opensrcerer.requests.media.StreamRequest;

import javax.annotation.Nonnull;
import java.util.Map;

public class AudioService {

    private static final AudioService audioService = new AudioService();

    private AudioService() {
    }

    public static AudioService getInstance() {
        return audioService;
    }

    @SuppressWarnings("ConstantConditions")
    public void addToQueueHandler(
            @Nonnull ButtonClickEvent event,
            @Nonnull PaginatedCursorized<EmbedEntity, Page> embed
    ) {
        AudioManager manager = event.getGuild().getAudioManager();
        String botChannelId = (manager.isConnected()) ? manager.getConnectedChannel().getId() : null;
        String memberChannelId = (event.getMember().getVoiceState().inVoiceChannel()) ?
                event.getMember().getVoiceState().getChannel().getId() : null;

        if (botChannelId == null && memberChannelId == null) {
            event.reply("You must join a voice channel to use this command!")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        if ((memberChannelId == null || !memberChannelId.equals(botChannelId)) && botChannelId != null) {
            event.reply("You must join <#" + botChannelId + "> to be able to add music!")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        if (botChannelId == null) {
            manager.openAudioConnection(event.getGuild().getVoiceChannelById(memberChannelId));
        }

        manager.setConnectionListener(new ConnectionListener() {
            @Override
            public void onPing(long ping) {
            }

            @Override
            public void onStatusChange(@NotNull ConnectionStatus status) {
                if (status.equals(ConnectionStatus.CONNECTED)) {
                    MusicPlayer.MUSIC_PLAYER.loadAndPlay(
                            event.getTextChannel(),
                            RequestFormatter.INSTANCE.getUrl(
                                    new StreamRequest(
                                            Map.of(
                                                    "id", embed.select().id(),
                                                    "maxBitRate", 128
                                            )
                                    ),
                                    event.getGuild().getId()
                            ).toString()
                    );
                }
            }

            @Override
            public void onUserSpeaking(@NotNull User user, boolean speaking) {
            }
        });

        event.deferEdit().queue();
    }
}
