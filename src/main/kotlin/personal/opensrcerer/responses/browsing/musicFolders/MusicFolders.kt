package personal.opensrcerer.responses.browsing.musicFolders

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import personal.opensrcerer.responses.SubsonicResponse

class MusicFolders : SubsonicResponse<MusicFolders>() {
    @JacksonXmlElementWrapper(localName = "musicFolders")
    val musicFolders: Array<MusicFolder>? = null
}