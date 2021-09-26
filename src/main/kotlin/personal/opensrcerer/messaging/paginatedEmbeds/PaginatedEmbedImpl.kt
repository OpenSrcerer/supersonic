package personal.opensrcerer.messaging.paginatedEmbeds

import net.dv8tion.jda.api.entities.MessageEmbed
import java.util.concurrent.atomic.AtomicInteger

open class PaginatedEmbedImpl(
    private val results: List<MessageEmbed>
) : PaginatedEmbed {
    private val page = AtomicInteger(0)

    override fun getPage(pageNumber: Int): MessageEmbed {
        if (pageNumber > results.size) {
            return results[results.size - 1]
        }
        return if (pageNumber < 1) {
            results[0]
        } else results[pageNumber]
    }

    override fun previous(skip: Int): MessageEmbed {
        return if (page.get() - skip < 0) {
            results[page.updateAndGet { 0 }]
        } else results[page.updateAndGet { p: Int -> p - skip }]
    }

    override fun previous(): MessageEmbed {
        return if (page.get() - 1 < 0) {
            results[page.get()]
        } else results[page.decrementAndGet()]
    }

    override fun current(): MessageEmbed {
        return results[page.get()]
    }

    override operator fun next(): MessageEmbed {
        return if (page.get() + 1 >= results.size) {
            results[page.get()]
        } else results[page.incrementAndGet()]
    }

    override fun next(skip: Int): MessageEmbed {
        return if (page.get() + skip > results.size - 1) {
            results[page.updateAndGet { results.size - 1 }]
        } else results[page.updateAndGet { p: Int -> p + skip }]
    }
}