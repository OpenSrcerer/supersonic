package personal.opensrcerer.messaging.pagination.search.entities;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import personal.opensrcerer.launch.SupersonicConstants;
import personal.opensrcerer.responses.entities.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumResult extends SearchResult {

    public AlbumResult(Album[] albums) {
        super(
                getResults(albums),
                SearchResultType.ALBUM
        );
    }

    private static List<MessageEmbed> getResults(Album[] albums) {
        var resultList = new ArrayList<MessageEmbed>();
        var builder = new EmbedBuilder();

        for (int index = 0, page = 0; index < albums.length; ++index) {
            if (page == SupersonicConstants.DEFAULT_PAGE_SIZE - 1) {
                resultList.add(builder.build());
                builder.clear();
                page = 0;
            }
            addFieldToBuilder(builder, albums[index].getTitle(), albums[index].getArtist(), false);
            ++page;
        }

        if (builder.isEmpty()) {
            resultList.add(builder.build());
        }
        return resultList;
    }

    private static void addFieldToBuilder(EmbedBuilder builder, String name, String value, boolean inline) {
        builder.addField(
                (name == null) ? "" : name,
                (value == null) ? "" : value,
                inline
        );
    }
}
