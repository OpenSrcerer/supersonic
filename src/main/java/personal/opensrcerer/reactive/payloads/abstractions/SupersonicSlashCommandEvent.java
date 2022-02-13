package personal.opensrcerer.reactive.payloads.abstractions;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyAction;

import javax.annotation.CheckReturnValue;
import java.util.Arrays;

public abstract class SupersonicSlashCommandEvent extends SupersonicEvent<SlashCommandEvent> {
    public SupersonicSlashCommandEvent(SlashCommandEvent event) {
        super(event);
    }

    @CheckReturnValue
    public ReplyAction replyEmbeds(MessageEmbed... embeds) {
        return rawEvent.replyEmbeds(Arrays.asList(embeds)).setEphemeral(true);
    }

    public Guild getGuild() {
        return rawEvent.getGuild();
    }

    public Member getMember() {
        return rawEvent.getMember();
    }
}
