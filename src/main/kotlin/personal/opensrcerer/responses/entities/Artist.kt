package personal.opensrcerer.responses.entities

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import java.util.*

data class Artist (
    @JacksonXmlProperty(localName = "id") val id: String,
    @JacksonXmlProperty(localName = "name") val name: String?,
    @JacksonXmlProperty(localName = "starred") val starred: Date?
)