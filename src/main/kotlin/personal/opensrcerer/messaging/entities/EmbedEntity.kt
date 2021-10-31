package personal.opensrcerer.messaging.entities

import net.dv8tion.jda.api.entities.MessageEmbed

interface EmbedEntity {
    /**
     * Return the Supersonic ID for the represented entity.
     */
    fun id() : String

    fun embedName() : String

    fun embedValue() : String

    fun asEmbedField() : MessageEmbed.Field {
        return MessageEmbed.Field(embedName(), embedValue(), false)
    }
}