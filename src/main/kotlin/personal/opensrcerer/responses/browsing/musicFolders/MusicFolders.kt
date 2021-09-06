package personal.opensrcerer.responses.browsing.musicFolders

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

class MusicFolders {
    @JacksonXmlElementWrapper
    val musicFolders: Array<MusicFolder>? = null

    data class MusicFolder (
        @JacksonXmlProperty(localName = "id") val id: Long,
        @JacksonXmlProperty(localName = "name") val name: String?
    )
}