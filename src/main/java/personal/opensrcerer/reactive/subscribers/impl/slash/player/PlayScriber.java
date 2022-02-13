package personal.opensrcerer.reactive.subscribers.impl.slash.player;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import personal.opensrcerer.aspect.annotations.BoundTo;
import personal.opensrcerer.aspect.annotations.Subscriber;
import personal.opensrcerer.aspect.mapping.EventMappingStrategy;
import personal.opensrcerer.client.SubsonicService;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.messaging.constant.ConstantEmbeds;
import personal.opensrcerer.reactive.payloads.Maybe;
import personal.opensrcerer.reactive.payloads.impl.slash.PlayEvent;
import personal.opensrcerer.reactive.subscribers.abstractions.SlashCommandSuperscriber;
import personal.opensrcerer.requests.RequestUtils;
import personal.opensrcerer.services.audio.AudioUtils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Subscriber(typeToHandle = SlashCommandEvent.class,
        strategy = EventMappingStrategy.SLASHCOMMAND_TO_PLAY)
@BoundTo(SlashCommand.PLAY)
public class PlayScriber extends SlashCommandSuperscriber<PlayEvent> {

    @Override
    public void onEvent(PlayEvent boxed) {
        Maybe<String> maybe = boxed.evaluate();
        if (!maybe.ok()) {
            boxed.replyEmbeds(maybe.err()).queue();
            return;
        }

        SubsonicService.INSTANCE.request(RequestUtils.INSTANCE.searchForSingleSong(maybe.value()),
                        boxed.getGuild().getId())
                .timeout(Duration.of(3000, ChronoUnit.MILLIS))
                .mapNotNull(result3 -> {
                    var songs = result3.getSongs();
                    if (songs == null) {
                        boxed.replyEmbeds(ConstantEmbeds.Companion.noResults()).queue();
                        return null;
                    }
                    return songs[0];
                })
                .doOnCancel(() -> boxed.replyEmbeds(ConstantEmbeds.Companion.timeout()).queue())
                .subscribe(song -> AudioUtils.playSongById(boxed.raw(), song));
    }
}
