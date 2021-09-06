package personal.opensrcerer.requests.media

import personal.opensrcerer.requests.RequestPath
import personal.opensrcerer.requests.SubsonicRequest
import java.io.Serializable

class StreamRequest(
    override val queryParams: Map<String, Serializable>
) : SubsonicRequest() {
    override val path: RequestPath = RequestPath.STREAM
}