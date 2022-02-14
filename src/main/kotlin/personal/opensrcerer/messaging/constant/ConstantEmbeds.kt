package personal.opensrcerer.messaging.constant

import net.dv8tion.jda.api.entities.MessageEmbed
import personal.opensrcerer.messaging.entities.EmbedEntity
import personal.opensrcerer.messaging.interfaces.embedInterfaces.QuickEmbed

class ConstantEmbeds {
    companion object {
        fun noResults(): MessageEmbed {
            return QuickEmbed.get { b -> b.addField(
                ConstantMessages.NO_RESULTS_EMBED_TITLE,
                ConstantMessages.NO_RESULTS_EMBED_DESCRIPTION,
                false
            )}
        }

        fun addedToQueue(title: String): MessageEmbed {
            return QuickEmbed.get { b -> b.addField(
                ConstantMessages.ADDED_TO_QUEUE_TITLE,
                "$title was successfully added to the queue.",
                false
            )}
        }

        fun plainEmbed(title: String, description: String): MessageEmbed {
            return QuickEmbed.get { b -> b.addField(
                title, description, false
            )}
        }

        fun timeout(): MessageEmbed {
            return QuickEmbed.get { b -> b.addField(
                "Request timed out",
                "This request took too long to complete.",
                false
            )}
        }
    }
}