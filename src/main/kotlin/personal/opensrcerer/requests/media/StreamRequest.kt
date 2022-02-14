package personal.opensrcerer.requests.media

import personal.opensrcerer.requests.subsonic.VoidRequest
import personal.opensrcerer.requests.RequestPath
import java.io.Serializable

class StreamRequest(
    override val queryParams: Map<String, Serializable>
) : VoidRequest() {

    constructor(id: String, bitrate: String) : this(
        mapOf(Pair("id", id), Pair("maxBitRate", bitrate))
    )

    override val path: RequestPath = RequestPath.STREAM
}