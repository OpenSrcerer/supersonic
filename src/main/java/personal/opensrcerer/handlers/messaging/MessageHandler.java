package personal.opensrcerer.handlers.messaging;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import personal.opensrcerer.client.SubsonicClient;
import personal.opensrcerer.handlers.FluxEventHandler;
import personal.opensrcerer.messaging.dto.ParsedGuildMessageEvent;
import personal.opensrcerer.requests.RequestPath;
import personal.opensrcerer.responses.ResponseWrapper;
import personal.opensrcerer.responses.browsing.musicFolders.MusicFolders;

import java.util.Arrays;
import java.util.function.Predicate;

public class MessageHandler implements FluxEventHandler<GuildMessageReceivedEvent, ParsedGuildMessageEvent> {

    public Predicate<GuildMessageReceivedEvent> predicate;

    public MessageHandler(Predicate<GuildMessageReceivedEvent> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void handle(ParsedGuildMessageEvent p) {
        if (p.botMentioned()) {

            ResponseWrapper<MusicFolders> response = SubsonicClient.INSTANCE.request(
                    MusicFolders.class, RequestPath.GET_MUSIC_FOLDERS, p.channel().getGuild().getId()
            );

            p.channel().sendMessage(
                    "folders: " + Arrays.toString(response.getParsed().getMusicFolders()) + "\n"
            ).queue();
        }
    }

    @Override
    public boolean isValid(GuildMessageReceivedEvent e) {
        return predicate.test(e);
    }
}
