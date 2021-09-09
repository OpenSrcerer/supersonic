package personal.opensrcerer.reactive.sinks.messaging;

import personal.opensrcerer.client.SubsonicClient;
import personal.opensrcerer.messaging.dto.ParsedGuildMessageEvent;
import personal.opensrcerer.requests.browsing.IndexesRequest;
import personal.opensrcerer.reactive.sinks.Sink;

import java.util.Arrays;

public class MessageSink implements Sink<ParsedGuildMessageEvent> {
    @Override
    public void receive(ParsedGuildMessageEvent p) {
        if (p.botMentioned()) {
            var e = SubsonicClient.INSTANCE.request(
                    new IndexesRequest(),
                    p.channel().getGuild().getId()
            );

            System.out.println(Arrays.toString(e.getIndex().toArray()));
        }
    }
}
