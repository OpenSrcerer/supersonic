package personal.opensrcerer.messaging

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed

fun interface QuickEmbed {
    fun form(builder: EmbedBuilder)

    companion object {
        fun get(q: QuickEmbed): MessageEmbed {
            val embedBuilder = EmbedBuilder()
            q.form(embedBuilder)
            return embedBuilder.build()
        }
    }
}