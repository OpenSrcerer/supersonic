package personal.opensrcerer.reactive.subscribers.impl.slash.player;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.aspect.annotations.BoundTo;
import personal.opensrcerer.aspect.annotations.Subscriber;
import personal.opensrcerer.aspect.mapping.EventMappingStrategy;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.messaging.constant.ConstantEmbeds;
import personal.opensrcerer.reactive.payloads.impl.slash.player.PauseEvent;
import personal.opensrcerer.reactive.subscribers.abstractions.SlashCommandSuperscriber;
import personal.opensrcerer.services.audio.MusicPlayer;

@Subscriber(typeToHandle = SlashCommandEvent.class,
        strategy = EventMappingStrategy.SLASHCOMMAND_TO_PAUSE)
@BoundTo(SlashCommand.PAUSE)
public class PauseScriber extends SlashCommandSuperscriber<PauseEvent> {
    @Override
    public void onEvent(PauseEvent boxed) {
        MusicPlayer.MUSIC_PLAYER.pause(boxed.getGuild());
        boxed.replyEmbeds(ConstantEmbeds.Companion.plainEmbed(
                "Player paused",
                "<@" + boxed.getMember().getId() + "> paused the player."
        )).queue();
    }
}
