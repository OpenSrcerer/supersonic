package personal.opensrcerer.messaging.paginatedEmbeds.search

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import personal.opensrcerer.launch.SupersonicConstants
import personal.opensrcerer.messaging.ConstantEmbeds
import personal.opensrcerer.messaging.paginatedEmbeds.PaginatedEmbedImpl
import personal.opensrcerer.responses.entities.EmbedMusicEntity

class SearchEmbedResult(
    val type: SearchEmbedType,
    entityList: Array<out EmbedMusicEntity>
) : PaginatedEmbedImpl(getResults(entityList)) {
    companion object {
        private fun getResults(entity: Array<out EmbedMusicEntity>): List<MessageEmbed> {
            if (entity.isEmpty()) {
                return listOf(ConstantEmbeds.NO_SEARCH_RESULTS)
            }

            val pagedResult = ArrayList<MessageEmbed>()
            val builder = EmbedBuilder()

            var page = 0
            for (element in entity) {
                if (page == SupersonicConstants.DEFAULT_PAGE_SIZE) {
                    pagedResult.add(builder.build())
                    builder.clear()
                    page = 0
                }
                builder.addField(element.asEmbedField())
                ++page
            }

            if (builder.isEmpty) {
                pagedResult.add(builder.build())
            }
            return pagedResult
        }
    }
}