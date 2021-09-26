package personal.opensrcerer.messaging

import net.dv8tion.jda.api.entities.MessageEmbed

class ConstantEmbeds {
    companion object {
        val NO_SEARCH_RESULTS: MessageEmbed = QuickEmbed.get { b ->
            b.addField(
                ConstantMessages.NO_RESULTS_EMBED_TITLE,
                ConstantMessages.NO_RESULTS_EMBED_DESCRIPTION,
                false
            )
        }
    }
}