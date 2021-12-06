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

    fun getEntityAtRow(row: Int): EmbedEntity {
        return entityPage[row]
    }

    fun getTotalRows(): Int {
        return entityPage.size
    }

    fun asMessageEmbed(selectedRow: Int): MessageEmbed {
        if (entityPage.isEmpty()) {
            return ConstantEmbeds.noResults()
        }

        var row = 0
        val builder = entityPage.fold(EmbedBuilder()) { b, e ->
            b.addField(e.asEmbedField(row == selectedRow))
            row++
            b
        }
        builder.setFooter("Page $pageNumber of $totalPages")
        return builder.build()
    }
}