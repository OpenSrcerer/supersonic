package personal.opensrcerer.requests.search

import personal.opensrcerer.requests.RequestPath
import personal.opensrcerer.requests.subsonic.SubsonicRequest
import personal.opensrcerer.responses.search.Result3
import java.io.Serializable

data class Search3(
    override val queryParams: Map<String, Serializable>
    ) : SubsonicRequest<Result3>() {

    override val path: RequestPath = RequestPath.SEARCH3

    override fun getClazz(): Class<out Result3> {
        return Result3::class.java
    }
}
