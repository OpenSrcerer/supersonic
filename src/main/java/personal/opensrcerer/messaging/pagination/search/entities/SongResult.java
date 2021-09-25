package personal.opensrcerer.messaging.pagination.search.entities;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import personal.opensrcerer.launch.SupersonicConstants;
import personal.opensrcerer.responses.entities.Song;

import java.util.ArrayList;
import java.util.List;

public class SongResult extends SearchResult {

    public SongResult(Song[] songs) {
        super(
                getResults(songs),
                SearchResultType.SONG
        );
    }

    private static List<MessageEmbed> getResults(Song[] songs) {
        var resultList = new ArrayList<MessageEmbed>();
        var builder = new EmbedBuilder();

        for (int index = 0, page = 0; index < songs.length; ++index) {
            if (page == SupersonicConstants.DEFAULT_PAGE_SIZE) {
                resultList.add(builder.build());
                builder.clear();
                page = 0;
            }
            addFieldToBuilder(builder, songs[index].getId() + " - " + songs[index].getTitle(), songs[index].getArtist(), false);
            ++page;
        }

        if (builder.isEmpty()) {
            resultList.add(builder.build());
        }
        return resultList;
    }

    private static void addFieldToBuilder(EmbedBuilder builder, String name, String value, boolean inline) {
        builder.addField(
                (name == null) ? "Unknown Title" : name,
                (value == null) ? "Unknown Artist" : value,
                inline
        );
    }
}
