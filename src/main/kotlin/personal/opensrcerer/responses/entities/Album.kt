package personal.opensrcerer.responses.entities

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class Album(
    @JacksonXmlProperty(localName = "id") val id: Long,
    @JacksonXmlProperty(localName = "parent") val parentId: Long?,
    @JacksonXmlProperty(localName = "title") val title: String?,
    @JacksonXmlProperty(localName = "artist") val artist: String?,
    @JacksonXmlProperty(localName = "isDir") val isDir: Boolean?,
    @JacksonXmlProperty(localName = "coverArt") val coverArt: String?
)