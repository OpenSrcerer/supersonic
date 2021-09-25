package personal.opensrcerer.reactive.sinks.slash.player;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.apache.commons.lang3.StringUtils;
import personal.opensrcerer.client.SubsonicClient;
import personal.opensrcerer.messaging.pagination.search.SearchEmbed;
import personal.opensrcerer.messaging.pagination.search.entities.AlbumResult;
import personal.opensrcerer.messaging.pagination.search.entities.ArtistResult;
import personal.opensrcerer.messaging.pagination.search.entities.SearchResultType;
import personal.opensrcerer.messaging.pagination.search.entities.SongResult;
import personal.opensrcerer.reactive.sinks.slash.SlashCommandSink;
import personal.opensrcerer.requests.search.Search3;
import personal.opensrcerer.responses.entities.Song;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class SearchSink extends SlashCommandSink {
    public SearchSink(Permission... permissions) {
        super(permissions);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onEvent(SlashCommandEvent event) {
        OptionMapping o = event.getOption("query");

        if (o == null) {
            event.reply("Missing query parameter!").queue();
            return;
        }

        var abc = SubsonicClient.INSTANCE.request(
                new Search3(
                        Map.of(
                                "query", o.getAsString(),
                                "songCount", "10"
                        )
                ),
                event.getGuild().getId()
        );

        if (abc.isEmpty()) {
            event.reply("No results!").queue();
            return;
        }

        SearchEmbed embed = new SearchEmbed(
                new SongResult(abc.getSongs()),
                new AlbumResult(abc.getAlbums()),
                new ArtistResult(abc.getArtists())
        );

        event.replyEmbeds(
                embed.getResultByType(SearchResultType.SONG).current()
        ).queue();
    }
}
