package personal.opensrcerer.reactive.subscribers.impl.slash.player;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.aspect.annotations.BoundTo;
import personal.opensrcerer.aspect.annotations.Subscriber;
import personal.opensrcerer.aspect.mapping.EventMappingStrategy;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.messaging.constant.ConstantEmbeds;
import personal.opensrcerer.reactive.payloads.impl.slash.UnpauseEvent;
import personal.opensrcerer.reactive.subscribers.abstractions.SlashCommandSuperscriber;
import personal.opensrcerer.services.audio.MusicPlayer;

@Subscriber(typeToHandle = SlashCommandEvent.class,
        strategy = EventMappingStrategy.SLASHCOMMAND_TO_UNPAUSE)
@BoundTo(SlashCommand.UNPAUSE)
public class UnpauseScriber extends SlashCommandSuperscriber<UnpauseEvent> {
    @Override
    public void onEvent(UnpauseEvent boxed) {
        MusicPlayer.MUSIC_PLAYER.unpause(boxed.getGuild());
        boxed.replyEmbeds(ConstantEmbeds.Companion.plainEmbed(
                "Player unpaused",
                "<@" + boxed.getMember().getId() + "> unpaused the player."
        )).queue();
    }
}
