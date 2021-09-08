package personal.opensrcerer.responses.entities

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class Artist (
    @JsonProperty("id") val id: String,
    @JsonProperty("name") val name: String?,
    @JsonProperty("starred") val starred: Date?
)