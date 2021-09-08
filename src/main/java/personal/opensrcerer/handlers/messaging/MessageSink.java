package personal.opensrcerer.handlers.messaging;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import personal.opensrcerer.client.SubsonicClient;
import personal.opensrcerer.handlers.Sink;
import personal.opensrcerer.messaging.dto.ParsedGuildMessageEvent;
import personal.opensrcerer.requests.browsing.MusicFoldersRequest;
import personal.opensrcerer.requests.search.Search2;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;

public class MessageSink implements Sink<GuildMessageReceivedEvent, ParsedGuildMessageEvent> {

    public Predicate<GuildMessageReceivedEvent> predicate;

    public MessageSink(Predicate<GuildMessageReceivedEvent> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void receive(ParsedGuildMessageEvent p) {
        if (p.botMentioned()) {
            var e = SubsonicClient.INSTANCE.request(
                    new Search2(Map.of("query", "local h")),
                    p.channel().getGuild().getId()
            );

            System.out.println(Arrays.toString(e.getArtists()));
        }
    }

    @Override
    public boolean isValid(GuildMessageReceivedEvent e) {
        return predicate.test(e);
    }
}
