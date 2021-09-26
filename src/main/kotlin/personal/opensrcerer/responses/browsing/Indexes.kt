package personal.opensrcerer.responses.browsing

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import personal.opensrcerer.responses.subsonic.SubsonicResponse
import personal.opensrcerer.responses.entities.Artist
import personal.opensrcerer.responses.entities.Song

class Indexes : SubsonicResponse() {

    @JacksonXmlElementWrapper
    private val indexes: IndexesData? = null

    data class IndexesData @JsonCreator constructor(
        @JsonProperty("lastModified")
        private val lastModified: String?,

        @JsonProperty("ignoredArticles")
        private val ignoredArticles: String?,

        @JsonProperty("child")
        @JacksonXmlElementWrapper(useWrapping = false)
        val children: Array<Song>? = null,

        @JsonProperty("shortcut")
        @JacksonXmlElementWrapper(useWrapping = false)
        val shortcuts: Array<Shortcut>? = null,

        @JsonProperty("index")
        @JacksonXmlElementWrapper(useWrapping = false)
        val indexes: Set<Index>? = null
    )

    data class Shortcut(
        val id: Long,
        val name: String
    )

    data class Index @JsonCreator constructor(
        @JsonProperty("name")
        val name: String,

        @JsonProperty("artist")
        @JacksonXmlElementWrapper(useWrapping = false)
        val artists: Array<Artist>?
    )

    fun getChildren(): Array<Song>? {
        return this.indexes?.children
    }

    fun getShortcuts(): Array<Shortcut>? {
        return this.indexes?.shortcuts
    }

    fun getIndex(): Set<Index>? {
        return this.indexes?.indexes
    }
}