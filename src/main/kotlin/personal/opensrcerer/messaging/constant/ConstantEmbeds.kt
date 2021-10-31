package personal.opensrcerer.messaging.constant

import net.dv8tion.jda.api.entities.MessageEmbed
import personal.opensrcerer.messaging.interfaces.embedInterfaces.QuickEmbed

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