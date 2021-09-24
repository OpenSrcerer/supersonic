package personal.opensrcerer.messaging.pagination.search;

import personal.opensrcerer.messaging.pagination.PaginatedEmbedImpl;
import personal.opensrcerer.messaging.pagination.search.entities.SearchResultType;
import personal.opensrcerer.responses.search.SearchResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class SearchEmbed {
    private final Map<SearchResultType, PaginatedEmbedImpl> map;

    public SearchEmbed(SearchResult result) {
        data = new ArrayList<>();

        (result.getSongs() == null) ?
                data.add(new ArrayList<>()) :
                data.add(
                        Arrays.asList(result.getSongs()).
                );
    }


}
