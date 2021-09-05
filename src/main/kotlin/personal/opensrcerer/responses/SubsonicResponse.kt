package personal.opensrcerer.responses

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "subsonic-response")
open class SubsonicResponse<T> {
    val data: T? = null
    val status: String = ""
    val xmlns: String = ""
    val version: String = ""
    val additionalProperties: Map<String, Any>? = null
}