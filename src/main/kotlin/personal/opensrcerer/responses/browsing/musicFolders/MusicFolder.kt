package personal.opensrcerer.responses.browsing.musicFolders

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class MusicFolder(
    @JacksonXmlProperty(localName = "id") val id: Long,
    @JacksonXmlProperty(localName = "name") val name: String
)