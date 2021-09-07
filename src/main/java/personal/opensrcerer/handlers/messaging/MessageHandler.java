package personal.opensrcerer.handlers.messaging;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import personal.opensrcerer.client.SubsonicClient;
import personal.opensrcerer.handlers.FluxEventHandler;
import personal.opensrcerer.messaging.dto.ParsedGuildMessageEvent;
import personal.opensrcerer.requests.browsing.MusicFoldersRequest;
import personal.opensrcerer.requests.media.StreamRequest;
import personal.opensrcerer.requests.search.Search2;

import java.util.Arrays;
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
            var e = SubsonicClient.INSTANCE.request(
                    new MusicFoldersRequest(),
                    p.channel().getGuild().getId()
            );

            var u = SubsonicClient.INSTANCE.request(
                    new Search2(Map.of("query", "insertartisthere")),
                    p.channel().getGuild().getId()
            );

            System.out.println(Arrays.toString(e.getMusicFolders()));
        }
    }

    @Override
    public boolean isValid(GuildMessageReceivedEvent e) {
        return predicate.test(e);
    }
}
