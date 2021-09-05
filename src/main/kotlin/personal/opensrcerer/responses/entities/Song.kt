package personal.opensrcerer.responses.entities

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class Song (
    @JacksonXmlProperty(localName = "id") val id: Long,
    @JacksonXmlProperty(localName = "parent") val parentId: Long?,
    @JacksonXmlProperty(localName = "title") val title: String?,
    @JacksonXmlProperty(localName = "isDir") val isDir: Boolean?,
    @JacksonXmlProperty(localName = "album") val album: String?,
    @JacksonXmlProperty(localName = "artist") val artist: String?,
    @JacksonXmlProperty(localName = "track") val track: Long?,
    @JacksonXmlProperty(localName = "year") val year: Int?,
    @JacksonXmlProperty(localName = "genre") val genre: String?,
    @JacksonXmlProperty(localName = "coverArt") val coverArt: Long?,
    @JacksonXmlProperty(localName = "size") val size: Long?,
    @JacksonXmlProperty(localName = "contentType") val contentType: String?,
    @JacksonXmlProperty(localName = "suffix") val suffix: String?,
    @JacksonXmlProperty(localName = "transcodedContentType") val transcodedContentType: String?,
    @JacksonXmlProperty(localName = "transcodedSuffix") val transcodedSuffix: String?,
    @JacksonXmlProperty(localName = "path") val path: String?
)