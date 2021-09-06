package personal.opensrcerer.handlers.messaging;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import personal.opensrcerer.client.SubsonicClient;
import personal.opensrcerer.handlers.FluxEventHandler;
import personal.opensrcerer.messaging.dto.ParsedGuildMessageEvent;
import personal.opensrcerer.requests.media.StreamRequest;

import java.util.Map;
import java.util.function.Predicate;

public class MessageHandler implements FluxEventHandler<GuildMessageReceivedEvent, ParsedGuildMessageEvent> {

    public Predicate<GuildMessageReceivedEvent> predicate;

    public MessageHandler(Predicate<GuildMessageReceivedEvent> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void handle(ParsedGuildMessageEvent p) {
        if (p.botMentioned()) {
            var e = SubsonicClient.INSTANCE.stream(
                    new StreamRequest(
                            Map.of("id", "2071")
                    ),
                    p.channel().getGuild().getId()
            );

            e.subscribe(s -> {

            });
        }
    }

    @Override
    public boolean isValid(GuildMessageReceivedEvent e) {
        return predicate.test(e);
    }
}
