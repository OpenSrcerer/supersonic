package personal.opensrcerer.reactive.handlers.messaging;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import personal.opensrcerer.messaging.dto.ParsedGuildMessageEvent;
import personal.opensrcerer.messaging.embeds.GenericEmbeds;
import personal.opensrcerer.reactive.handlers.EventHandler;

import java.util.function.Predicate;

public class MessageHandler implements EventHandler<GuildMessageReceivedEvent, ParsedGuildMessageEvent> {

    public Predicate<GuildMessageReceivedEvent> predicate;

    public MessageHandler(Predicate<GuildMessageReceivedEvent> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void handle(ParsedGuildMessageEvent p) {
        if (p.botMentioned() && p.parsed().size() >= 2 && p.parsed().get(1).equals("pugpanel")) {
            p.channel().sendMessageEmbeds(GenericEmbeds.getPugEmbed())
                    .reference(p.eventMessage())
                    .setActionRows(
                            ActionRow.of(
                                    Button.danger("rpugroles", "Remove Pug's Roles"),
                                    Button.danger("addpugroles", "Give Pug's Roles Back")
                            ),
                            ActionRow.of(
                                    Button.primary("mutepug", "Mute Pug"),
                                    Button.primary("unmutepug", "Unmute Pug")
                            ),
                            ActionRow.of(
                                    Button.primary("senddm", "Send Pug a Funny DM")
                            )
                    )
                    .queue();
            return;
        }

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
