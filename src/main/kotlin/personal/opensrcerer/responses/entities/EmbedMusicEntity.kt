package personal.opensrcerer.responses.entities

import net.dv8tion.jda.api.entities.MessageEmbed

interface EmbedMusicEntity {
    fun embedName() : String

    fun embedValue() : String

    fun asEmbedField() : MessageEmbed.Field {
        return MessageEmbed.Field(embedName(), embedValue(), false)
    }
}