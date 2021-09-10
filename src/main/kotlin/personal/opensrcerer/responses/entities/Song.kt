package personal.opensrcerer.responses.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Song(
    @JsonProperty("id") val id: Long,
    @JsonProperty("parent") val parent: Long?,
    @JsonProperty("title") val title: String?,
    @JsonProperty("isDir") val isDir: Boolean,
    @JsonProperty("isVideo") val isVideo: Boolean,
    @JsonProperty("album") val album: String?,
    @JsonProperty("artist") val artist: String?,
    @JsonProperty("track") val track: Long?,
    @JsonProperty("year") val year: String?,
    @JsonProperty("genre") val genre: String?,
    @JsonProperty("coverArt") val coverArt: Long?,
    @JsonProperty("size") val size: String?,
    @JsonProperty("contentType") val contentType: String?,
    @JsonProperty("suffix") val suffix: String?,
    @JsonProperty("transcodedContentType") val transcodedContentType: String?,
    @JsonProperty("transcodedSuffix") val transcodedSuffix: String?,
    @JsonProperty("duration") val duration: Long?,
    @JsonProperty("bitRate") val bitrate: Int?,
    @JsonProperty("path") val path: String?
)