package personal.opensrcerer.messaging.impl.paginatedEmbeds

import personal.opensrcerer.messaging.entities.EmbedEntity
import personal.opensrcerer.messaging.entities.Page
import personal.opensrcerer.messaging.interfaces.embedInterfaces.Cursorized
import personal.opensrcerer.messaging.interfaces.embedInterfaces.Paginated
import java.util.concurrent.atomic.AtomicInteger

open class ReactiveEmbed(
    private val pages: List<Page>
) : Paginated<Page>, Cursorized<EmbedEntity> {
    private val currentPage = AtomicInteger(0)
    private val currentRow = AtomicInteger(if (pages.isEmpty()) -1 else 0)

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
        return if (currentPage.get() - skip < 0 || currentPage.get() + skip > pages.size - 1) {
            pages[currentPage.updateAndGet { 0 }]
        } else pages[currentPage.updateAndGet { p: Int -> p - skip }]
    }

    override fun previous(): Page? {
        if (pages.isEmpty()) return null
        return if (currentPage.get() - 1 < 0) {
            pages[currentPage.get()]
        } else pages[currentPage.decrementAndGet()]
    }

    override fun current(): Page? {
        return if (pages.isEmpty()) null else pages[currentPage.get()]
    }

    override operator fun next(): Page? {
        if (pages.isEmpty()) return null
        return if (currentPage.get() + 1 >= pages.size) {
            pages[currentPage.get()]
        } else pages[currentPage.incrementAndGet()]
    }

    override fun next(skip: Int): Page? {
        if (pages.isEmpty()) return null
        return if (currentPage.get() + skip > pages.size - 1 || currentPage.get() + skip < 0) {
            pages[currentPage.updateAndGet { pages.size - 1 }]
        } else pages[currentPage.updateAndGet { p: Int -> p + skip }]
    }

    /* Implementation for Cursorization */

    override fun up() {
        up(1)
    }

    override fun up(scrollBy: Int) {
        if (currentRow.get() <= 0) {
            return
        }
        currentRow.incrementAndGet()
    }

    override fun select(): EmbedEntity? {
        if (pages.isEmpty()) return null
        return pages[currentPage.get()].getEntityAtRow(currentRow.get())
    }

    override fun down(scrollBy: Int) {
        if (currentRow.get() + 1 >= pages[currentPage.get()].getTotalRows()) {
            return
        }
        currentRow.incrementAndGet()
    }

    override fun down() {
        down(1)
    }
}