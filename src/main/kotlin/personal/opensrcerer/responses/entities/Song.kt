package personal.opensrcerer.responses.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class Song (
    @JsonProperty("id") val id: Long,
    @JsonProperty("parent") val parentId: Long?,
    @JsonProperty("title") val title: String?,
    @JsonProperty("isDir") val isDir: Boolean?,
    @JsonProperty("album") val album: String?,
    @JsonProperty("artist") val artist: String?,
    @JsonProperty("track") val track: Long?,
    @JsonProperty("year") val year: Int?,
    @JsonProperty("genre") val genre: String?,
    @JsonProperty("coverArt") val coverArt: Long?,
    @JsonProperty("size") val size: Long?,
    @JsonProperty("contentType") val contentType: String?,
    @JsonProperty("suffix") val suffix: String?,
    @JsonProperty("transcodedContentType") val transcodedContentType: String?,
    @JsonProperty("transcodedSuffix") val transcodedSuffix: String?,
    @JsonProperty("path") val path: String?
)