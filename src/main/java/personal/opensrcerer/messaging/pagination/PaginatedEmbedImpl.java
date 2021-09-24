package personal.opensrcerer.messaging.pagination;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class PaginatedEmbedImpl implements PaginatedEmbed {
    private final AtomicInteger page = new AtomicInteger(0);
    private final List<MessageEmbed> results;

    public PaginatedEmbedImpl(List<MessageEmbed> results) {
        this.results = results;
    }

    @Override
    public MessageEmbed getPage(int pageNumber) {
        if (pageNumber > results.size()) {
            return results.get(results.size() - 1);
        }

        if (pageNumber < 1) {
            return results.get(0);
        }

        return results.get(pageNumber);
    }

    @Override
    public MessageEmbed previous(int skip) {
        if (page.get() - skip < 0) {
            return results.get(page.updateAndGet(p -> 0));
        }
        return results.get(page.updateAndGet(p -> p - skip));
    }

    @Override
    public MessageEmbed previous() {
        if (page.get() - 1 < 0) {
            return results.get(page.get());
        }
        return results.get(page.decrementAndGet());
    }

    @Override
    public MessageEmbed current() {
        return results.get(page.get());
    }

    @Override
    public MessageEmbed next() {
        if (page.get() + 1 >= results.size()) {
            return results.get(page.get());
        }
        return results.get(page.incrementAndGet());
    }

    @Override
    public MessageEmbed next(int skip) {
        if (page.get() + skip > results.size() - 1) {
            return results.get(page.updateAndGet(p -> results.size() - 1));
        }
        return results.get(page.updateAndGet(p -> p + skip));
    }
}
