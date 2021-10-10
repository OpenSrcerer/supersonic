package personal.opensrcerer.responses.entities

import com.fasterxml.jackson.annotation.JsonProperty
import personal.opensrcerer.responses.enum.Unknown

class Album(
    @JsonProperty("id")       id: String?,
    @JsonProperty("parent")   parent: String?,
    @JsonProperty("name")     name: String?,
    @JsonProperty("artist")   artist: String?,
    @JsonProperty("isDir")    isDir: String?,
    @JsonProperty("coverArt") coverArt: String?
) : EmbedMusicEntity {

    val id = id ?: Unknown.ID.value
    val parent = parent ?: Unknown.PARENT.value
    val name = name ?: Unknown.TITLE.value
    val artist = artist ?: Unknown.ARTIST.value
    val isDir = isDir ?: Unknown.IS_DIRECTORY.value
    val coverArt = coverArt ?: Unknown.COVER_ART.value

    override fun embedName(): String {
        return "[$id] / $name"
    }

    override fun embedValue(): String {
        return artist
    }
}