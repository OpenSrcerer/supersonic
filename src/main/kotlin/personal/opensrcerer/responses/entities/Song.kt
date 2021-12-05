package personal.opensrcerer.responses.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import personal.opensrcerer.messaging.entities.EmbedEntity
import personal.opensrcerer.responses.enum.Unknown

@JsonIgnoreProperties(ignoreUnknown = true)
class Song(
    @JsonProperty("id")                    id: String?,
    @JsonProperty("parent")                parent: String?,
    @JsonProperty("title")                 title: String?,
    @JsonProperty("isDir")                 isDir: String?,
    @JsonProperty("isVideo")               isVideo: String?,
    @JsonProperty("album")                 album: String?,
    @JsonProperty("artist")                artist: String?,
    @JsonProperty("track")                 track: String?,
    @JsonProperty("year")                  year: String?,
    @JsonProperty("genre")                 genre: String?,
    @JsonProperty("coverArt")              coverArt: String?,
    @JsonProperty("size")                  size: String?,
    @JsonProperty("contentType")           contentType: String?,
    @JsonProperty("suffix")                suffix: String?,
    @JsonProperty("transcodedContentType") transcodedContentType: String?,
    @JsonProperty("transcodedSuffix")      transcodedSuffix: String?,
    @JsonProperty("duration")              duration: String?,
    @JsonProperty("bitRate")               bitrate: String?,
    @JsonProperty("path")                  path: String?
) : EmbedEntity {

    val id = id ?: Unknown.ID.value
    val parent = parent ?: Unknown.PARENT.value
    val title = title ?: Unknown.TITLE.value
    val isDir = isDir ?: Unknown.IS_DIRECTORY.value
    val isVideo = isVideo ?: Unknown.IS_VIDEO.value
    val album = album ?: Unknown.ALBUM.value
    val artist = artist ?: Unknown.ARTIST.value
    val track = track ?: Unknown.TRACK.value
    val year = year ?: Unknown.YEAR.value
    val genre = genre ?: Unknown.GENRE.value
    val coverArt = coverArt ?: Unknown.COVER_ART.value
    val size = size ?: Unknown.SIZE.value
    val contentType = contentType ?: Unknown.CONTENT_TYPE.value
    val suffix = suffix ?: Unknown.SUFFIX.value
    val transcodedContentType = transcodedContentType ?: Unknown.TRANSCODED_CONTENT_TYPE.value
    val transcodedSuffix = transcodedSuffix ?: Unknown.TRANSCODED_SUFFIX.value
    val duration = duration ?: Unknown.DURATION.value
    val bitrate = bitrate ?: Unknown.BITRATE.value
    val path = path ?: Unknown.PATH.value

    override fun id(): String {
        return id
    }

    override fun embedName(): String {
        return title
    }

    override fun embedValue(): String {
        return "$artist - $album"
    }
}