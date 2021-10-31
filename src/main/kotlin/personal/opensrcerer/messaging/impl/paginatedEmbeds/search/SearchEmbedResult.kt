package personal.opensrcerer.messaging.impl.paginatedEmbeds.search

import personal.opensrcerer.launch.SupersonicConstants
import personal.opensrcerer.messaging.entities.EmbedEntity
import personal.opensrcerer.messaging.entities.Page
import personal.opensrcerer.messaging.impl.paginatedEmbeds.ReactiveEmbed

class SearchEmbedResult(
    val type: SearchEmbedType,
    entityList: Array<out EmbedEntity>
) : ReactiveEmbed(getResults(entityList)) {
    companion object {
        private fun getResults(
            entityArray: Array<out EmbedEntity>,
            pageSize: Int = SupersonicConstants.DEFAULT_PAGE_SIZE
        ): List<Page> {
            if (entityArray.isEmpty()) {
                return emptyList()
            }

            val pagedResult = ArrayList<Page>()
            val totalPages: Int =
                if (entityArray.size % pageSize == 0)
                    entityArray.size / pageSize
                else entityArray.size / pageSize + 1

            for (i in 1 until totalPages + 1) {
                val begin = pageSize * (i - 1)
                val end =
                    if (i * pageSize >= entityArray.size)
                        entityArray.size - 1
                    else i * pageSize - 1

                val page = Page(i, totalPages)
                page.addRows(entityArray.sliceArray(begin..end))
                pagedResult.add(page)
            }

            return pagedResult
        }
    }
}