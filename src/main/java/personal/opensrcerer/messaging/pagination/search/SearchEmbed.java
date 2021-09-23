package personal.opensrcerer.messaging.pagination.search;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import personal.opensrcerer.responses.search.SearchResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SearchEmbed {
    private AtomicInteger page = new AtomicInteger();

    private String defaultTitle;
    private String defaultDescription;
    private String defaultFooter;

    private final List<List<MessageEmbed>> data;

    public SearchEmbed(SearchResult result) {
        data = new ArrayList<>();

        (result.getSongs() == null) ?
                data.add(new ArrayList<>()) :
                data.add(
                        Arrays.asList(result.getSongs()).
                );
    }

    public void addPage(EmbedBuilder rawPage) {
        pages.add(rawPage.build());
    }

    public MessageEmbed getPage(int pageNumber) {
        return pages.get(pageNumber - 1);
    }

    public void removePage(int pageNumber) {
        pages.remove(pageNumber - 1);
    }
}
