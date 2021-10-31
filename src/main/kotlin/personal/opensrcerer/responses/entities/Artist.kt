package personal.opensrcerer.responses.entities

import com.fasterxml.jackson.annotation.JsonProperty
import personal.opensrcerer.messaging.entities.EmbedEntity
import personal.opensrcerer.responses.enum.Unknown
import java.util.*

class Artist (
    @JsonProperty("id")      id: String?,
    @JsonProperty("name")    name: String?,
    @JsonProperty("starred") val starred: Date?
) : EmbedEntity {

    val id = id ?: Unknown.ID.value
    val name = name ?: Unknown.ARTIST_NAME.value

    override fun id(): String {
        return id
    }

    override fun embedName(): String {
        return name
    }

    override fun embedValue(): String {
        return id
    }
}