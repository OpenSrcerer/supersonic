package personal.opensrcerer.messaging.pagination.search;

import personal.opensrcerer.messaging.pagination.PaginatedEmbedImpl;
import personal.opensrcerer.messaging.pagination.search.entities.SearchResult;
import personal.opensrcerer.messaging.pagination.search.entities.SearchResultType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SearchEmbed {
    private final Map<SearchResultType, PaginatedEmbedImpl> map;

    public SearchEmbed(SearchResult... results) {
        map = new HashMap<>();
        Arrays.stream(results).forEach(
                r -> map.put(r.getType(), r)
        );
    }

    public PaginatedEmbedImpl getResultByType(SearchResultType type) {
        return map.get(type);
    }
}
