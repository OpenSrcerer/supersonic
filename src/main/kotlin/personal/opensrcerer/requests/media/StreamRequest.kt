package personal.opensrcerer.requests.media

import personal.opensrcerer.requests.VoidRequest
import personal.opensrcerer.requests.RequestPath
import java.io.Serializable

class StreamRequest(
    override val queryParams: Map<String, Serializable>
) : VoidRequest() {
    override val path: RequestPath = RequestPath.STREAM
}