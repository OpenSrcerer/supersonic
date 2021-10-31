package personal.opensrcerer.messaging.entities

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import personal.opensrcerer.messaging.constant.ConstantEmbeds

class Page(
    private val pageNumber: Int,
    private val totalPages: Int
) {
    private val entityPage = ArrayList<EmbedEntity>()

    fun addRows(elements: Array<out EmbedEntity>) {
        entityPage.addAll(elements)
    }

    companion object {
        fun asMessageEmbed(page: Page?): MessageEmbed {
            if (page == null || page.entityPage.isEmpty()) {
                return ConstantEmbeds.NO_SEARCH_RESULTS
            }

            val builder = page.entityPage.fold(EmbedBuilder()) { b, e -> b.addField(e.asEmbedField()) }
            builder.setFooter("Page ${page.pageNumber} of ${page.totalPages}")
            return builder.build()
        }
    }
}