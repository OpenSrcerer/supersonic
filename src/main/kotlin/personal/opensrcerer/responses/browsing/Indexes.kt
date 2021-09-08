package personal.opensrcerer.responses.browsing

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import personal.opensrcerer.responses.SubsonicResponse
import personal.opensrcerer.responses.entities.Artist
import personal.opensrcerer.responses.entities.Child

class Indexes : SubsonicResponse() {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonIgnoreProperties(ignoreUnknown = true)
    val child: Array<Child>? = null

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonIgnoreProperties(ignoreUnknown = true)
    val shortcut: Array<Shortcut>? = null

    @JacksonXmlElementWrapper
    @JsonIgnoreProperties(ignoreUnknown = true)
    val index: Set<Index>? = null

    data class Shortcut(
        val id: Long,
        val name: String
    )

    data class Index(
        val name: String,
        @JacksonXmlElementWrapper(localName = "artist")
        val index: Array<Artist>
    )
}