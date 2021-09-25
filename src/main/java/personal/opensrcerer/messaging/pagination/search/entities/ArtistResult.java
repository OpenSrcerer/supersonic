package personal.opensrcerer.messaging.pagination.search.entities;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import personal.opensrcerer.launch.SupersonicConstants;
import personal.opensrcerer.responses.entities.Artist;

import java.util.ArrayList;
import java.util.List;

public class ArtistResult extends SearchResult {

    public ArtistResult(Artist[] artists) {
        super(
                getResults(artists),
                SearchResultType.ARTIST
        );
    }

    private static List<MessageEmbed> getResults(Artist[] artists) {
        var resultList = new ArrayList<MessageEmbed>();
        var builder = new EmbedBuilder();

        for (int index = 0, page = 0; index < artists.length; ++index) {
            if (page == SupersonicConstants.DEFAULT_PAGE_SIZE - 1) {
                resultList.add(builder.build());
                builder.clear();
                page = 0;
            }
            addFieldToBuilder(builder, artists[index].getName(), artists[index].getId(), false);
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
