package personal.opensrcerer.responses

import com.fasterxml.jackson.dataformat.xml.XmlMapper

class ResponseWrapper<T>(
    clazz: Class<T>,
    response: String
) where T : SubsonicResponse<T> {

    val data: T

    init {
        this.data = xmlMapper.readValue(response, clazz)
    }

    private companion object {
        val xmlMapper = XmlMapper()
    }
}