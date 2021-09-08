package personal.opensrcerer.responses.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class Album(
    @JsonProperty("id") val id: Long,
    @JsonProperty("parent") val parent: Long?,
    @JsonProperty("title") val title: String?,
    @JsonProperty("artist") val artist: String?,
    @JsonProperty("isDir") val isDir: Boolean?,
    @JsonProperty("coverArt") val coverArt: String?
)