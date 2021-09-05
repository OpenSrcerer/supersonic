package personal.opensrcerer.requests.search

import personal.opensrcerer.requests.RequestPath
import personal.opensrcerer.requests.SubsonicRequest
import java.io.Serializable

data class Search2(
    override val queryParams: Map<String, Serializable>
    ) : SubsonicRequest() {

    override val path: RequestPath = RequestPath.SEARCH2
}
