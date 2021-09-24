package personal.opensrcerer.messaging.pagination.search.entities;

import net.dv8tion.jda.api.entities.MessageEmbed;
import personal.opensrcerer.messaging.pagination.PaginatedEmbedImpl;

import java.util.List;

public abstract class SearchResult extends PaginatedEmbedImpl {

    private final SearchResultType type;

    public SearchResult(List<MessageEmbed> results, SearchResultType type) {
        super(results);
        this.type = type;
    }
}
