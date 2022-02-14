package personal.opensrcerer.reactive.subscribers.impl.slash.player;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.aspect.annotations.BoundTo;
import personal.opensrcerer.aspect.annotations.Subscriber;
import personal.opensrcerer.aspect.mapping.EventMappingStrategy;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.messaging.constant.ConstantEmbeds;
import personal.opensrcerer.reactive.payloads.Maybe;
import personal.opensrcerer.reactive.payloads.impl.slash.JoinEvent;
import personal.opensrcerer.reactive.subscribers.abstractions.SlashCommandSuperscriber;

@Subscriber(typeToHandle = SlashCommandEvent.class,
        strategy = EventMappingStrategy.SLASHCOMMAND_TO_JOIN)
@BoundTo(SlashCommand.JOIN)
public class JoinScriber extends SlashCommandSuperscriber<JoinEvent> {
    @Override
    public void onEvent(JoinEvent boxed) {
        Maybe<Boolean> maybe = boxed.evaluate();
        if (!maybe.ok()) {
            boxed.replyEmbeds(maybe.err()).queue();
            return;
        }

        boxed.getAudioManager().openAudioConnection(boxed.getMemberVoiceChannel());
        if (maybe.value()) {
            boxed.replyEmbeds(ConstantEmbeds.Companion.plainEmbed(
                    "Switched channels",
                    "Switched channel to: <#" + boxed.getMemberVoiceChannel().getId() + ">"
            )).queue();
        } else {
            boxed.replyEmbeds(ConstantEmbeds.Companion.plainEmbed(
                    "Joined your channel",
                    "Joined <#" + boxed.getMemberVoiceChannel().getId() + ">"
            )).queue();
        }
    }
}
