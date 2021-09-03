package personal.opensrcerer.handlers.messaging;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import personal.opensrcerer.client.SubsonicClient;
import personal.opensrcerer.messaging.dto.ParsedGuildMessageEvent;
import personal.opensrcerer.handlers.FluxEventHandler;
import personal.opensrcerer.requests.RequestType;
import personal.opensrcerer.responses.SubsonicData;
import personal.opensrcerer.responses.SubsonicResponse;
import personal.opensrcerer.responses.browsing.musicFolders.MusicFolders;

import java.util.function.Predicate;

public class MessageHandler implements FluxEventHandler<GuildMessageReceivedEvent, ParsedGuildMessageEvent> {

    public Predicate<GuildMessageReceivedEvent> predicate;

    public MessageHandler(Predicate<GuildMessageReceivedEvent> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void handle(ParsedGuildMessageEvent p) {
        if (p.botMentioned()) {

            SubsonicResponse<SubsonicData> response = SubsonicClient.INSTANCE.<MusicFolders>request(
                    RequestType.PING, p.channel().getGuild().getId()
            );

            p.channel().sendMessage().queue();
        }
    }

    @Override
    public boolean isValid(GuildMessageReceivedEvent e) {
        return predicate.test(e);
    }
}
