package personal.opensrcerer.requests.search

import personal.opensrcerer.requests.RequestPath
import personal.opensrcerer.requests.SubsonicRequest
import java.io.Serializable

data class Search3(
    override val queryParams: Map<String, Serializable>
    ) : SubsonicRequest<Search3>() {

    override val path: RequestPath = RequestPath.SEARCH3

    override fun getClazz(): Class<out Search3> {
        return Search3::class.java
    }
}
