package personal.opensrcerer.messaging.pagination.search.entities;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import personal.opensrcerer.responses.entities.Album;

import java.util.List;

public class AlbumResult extends SearchResult {

    public AlbumResult(Album[] albums, SearchResultType type) {
        super(getResults(albums), type);
    }

    private static List<MessageEmbed> getResults(Album[] albums) {
        var builder = new EmbedBuilder();

        for (Album album : albums) {
            builder.addField(album.getTitle(), album.getArtist(), false);
        }

        return builder.build();
    }
}
