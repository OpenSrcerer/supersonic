package personal.opensrcerer.responses

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "subsonic-response")
@JsonIgnoreProperties(ignoreUnknown = true)
open class SubsonicResponse<T> {
    val status: String = ""
    val xmlns: String = ""
    val version: String = ""
}