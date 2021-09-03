package personal.opensrcerer.responses

import com.fasterxml.jackson.dataformat.xml.XmlMapper

class SubsonicResponse<T> (
    private val clazz: Class<T>,
    response: String,
    val status: Int
    ) where T : SubsonicData {

    val parsedBody: T

    init {
        this.parsedBody = parseBody(response)
    }

    private fun parseBody(body: String): T {
        val xmlMapper = XmlMapper()
        val e: T  = xmlMapper.readValue(body, clazz)
        println(e)
        return e
    }
}