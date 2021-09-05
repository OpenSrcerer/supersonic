package personal.opensrcerer.handlers.messaging;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import personal.opensrcerer.client.SubsonicClient;
import personal.opensrcerer.handlers.FluxEventHandler;
import personal.opensrcerer.messaging.dto.ParsedGuildMessageEvent;
import personal.opensrcerer.requests.system.Ping;
import personal.opensrcerer.responses.ResponseWrapper;
import personal.opensrcerer.responses.system.Pong;

import java.util.function.Predicate;

public class MessageHandler implements FluxEventHandler<GuildMessageReceivedEvent, ParsedGuildMessageEvent> {

    public Predicate<GuildMessageReceivedEvent> predicate;

    public MessageHandler(Predicate<GuildMessageReceivedEvent> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void handle(ParsedGuildMessageEvent p) {
        if (p.botMentioned()) {
            ResponseWrapper<Pong> response = SubsonicClient.INSTANCE.request(
                    Pong.class,
                    new Ping(),
                    p.channel().getGuild().getId()
            );

            p.channel().sendMessage("value: " + response.getData()).queue();
        }
    }

    @Override
    public boolean isValid(GuildMessageReceivedEvent e) {
        return predicate.test(e);
    }
}
