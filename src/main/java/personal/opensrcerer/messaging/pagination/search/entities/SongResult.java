package personal.opensrcerer.messaging.pagination.search.entities;

import net.dv8tion.jda.api.entities.MessageEmbed;
import personal.opensrcerer.responses.entities.Song;

import java.util.List;

public class SongResult extends SearchResult {
    public SongResult(Song[] songs, SearchResultType type) {
        super(results, type);
    }
}
