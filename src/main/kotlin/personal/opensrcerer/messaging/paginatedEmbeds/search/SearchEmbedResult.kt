package personal.opensrcerer.messaging.paginatedEmbeds.search

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import personal.opensrcerer.launch.SupersonicConstants
import personal.opensrcerer.messaging.constant.ConstantEmbeds
import personal.opensrcerer.messaging.paginatedEmbeds.PaginatedEmbedImpl
import personal.opensrcerer.messaging.entities.EmbedEntity

class SearchEmbedResult(
    val type: SearchEmbedType,
    entityList: Array<out EmbedEntity>
) : PaginatedEmbedImpl(getResults(entityList)) {
    companion object {
        private fun getResults(entity: Array<out EmbedEntity>): List<MessageEmbed> {
            if (entity.isEmpty()) {
                return listOf(ConstantEmbeds.NO_SEARCH_RESULTS)
            }

            val pagedResult = ArrayList<MessageEmbed>()
            val builder = EmbedBuilder()
            val totalPages: Int =
                if (entity.size % SupersonicConstants.DEFAULT_PAGE_SIZE == 0)
                    entity.size / SupersonicConstants.DEFAULT_PAGE_SIZE
                else entity.size / SupersonicConstants.DEFAULT_PAGE_SIZE + 1

            var page = 0
            var pageSize = 0
            for (element in entity) {
                if (pageSize == SupersonicConstants.DEFAULT_PAGE_SIZE) {
                    builder.setFooter("Page ${page + 1} of $totalPages")
                    pagedResult.add(builder.build())
                    builder.clear()
                    pageSize = 0
                    ++page
                }
                builder.addField(element.asEmbedField())
                ++pageSize
            }

            if (!builder.isEmpty) {
                builder.setFooter("Page ${page + 1} of $totalPages")
                pagedResult.add(builder.build())
            }

            return pagedResult
        }
    }
}