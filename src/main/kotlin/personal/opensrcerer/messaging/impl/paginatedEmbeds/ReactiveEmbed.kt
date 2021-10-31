package personal.opensrcerer.messaging.impl.paginatedEmbeds

import personal.opensrcerer.messaging.entities.EmbedEntity
import personal.opensrcerer.messaging.entities.Page
import personal.opensrcerer.messaging.interfaces.embedInterfaces.CursorizedEmbed
import personal.opensrcerer.messaging.interfaces.embedInterfaces.PaginatedEmbed
import java.util.concurrent.atomic.AtomicInteger

open class ReactiveEmbed(
    private val pages: List<Page>
) : PaginatedEmbed<Page>, CursorizedEmbed {
    private val page = AtomicInteger(0)
    private val row = AtomicInteger(if (pages.isEmpty()) -1 else 0)

    /* Implementation for Pagination */

    override fun getPage(pageNumber: Int): Page? {
        if (pages.isEmpty()) return null
        if (pageNumber > pages.size) {
            return pages[pages.size - 1]
        }
        return if (pageNumber < 1) {
            pages[0]
        } else pages[pageNumber - 1]
    }

    override fun previous(skip: Int): Page? {
        if (pages.isEmpty()) return null
        return if (page.get() - skip < 0 || page.get() + skip > pages.size - 1) {
            pages[page.updateAndGet { 0 }]
        } else pages[page.updateAndGet { p: Int -> p - skip }]
    }

    override fun previous(): Page? {
        if (pages.isEmpty()) return null
        return if (page.get() - 1 < 0) {
            pages[page.get()]
        } else pages[page.decrementAndGet()]
    }

    override fun current(): Page? {
        if (pages.isEmpty()) return null
        return pages[page.get()]
    }

    override operator fun next(): Page? {
        if (pages.isEmpty()) return null
        return if (page.get() + 1 >= pages.size) {
            pages[page.get()]
        } else pages[page.incrementAndGet()]
    }

    override fun next(skip: Int): Page? {
        if (pages.isEmpty()) return null
        return if (page.get() + skip > pages.size - 1 || page.get() + skip < 0) {
            pages[page.updateAndGet { pages.size - 1 }]
        } else pages[page.updateAndGet { p: Int -> p + skip }]
    }

    /* Implementation for Cursorization */

    override fun up(scrollBy: Int) {
        row.decrementAndGet()
    }

    override fun down(scrollBy: Int) {
        row.incrementAndGet()
    }

    override fun select(): EmbedEntity {
        TODO("Not yet implemented")
    }
}