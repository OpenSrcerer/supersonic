package personal.opensrcerer.reactive.handlers.messaging;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.Button;
import personal.opensrcerer.messaging.dto.ParsedGuildMessageEvent;
import personal.opensrcerer.messaging.embeds.GenericEmbeds;
import personal.opensrcerer.reactive.handlers.FluxEventHandler;

import java.util.function.Predicate;

public class MessageHandler implements FluxEventHandler<GuildMessageReceivedEvent, ParsedGuildMessageEvent> {

    public Predicate<GuildMessageReceivedEvent> predicate;

    public MessageHandler(Predicate<GuildMessageReceivedEvent> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void handle(ParsedGuildMessageEvent p) {
        if (p.botMentioned()) {
            p.channel().sendMessageEmbeds(GenericEmbeds.getEmbed())
                    .reference(p.eventMessage())
                    .setActionRow(Button.primary("OK", "OK"))
                    .queue();
        }
    }

    @Override
    public boolean isValid(GuildMessageReceivedEvent e) {
        return predicate.test(e);
    }
}
