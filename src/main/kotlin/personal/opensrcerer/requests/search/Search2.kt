package personal.opensrcerer.requests.search

import personal.opensrcerer.requests.RequestPath
import personal.opensrcerer.requests.SubsonicRequest
import personal.opensrcerer.responses.search.Result2
import java.io.Serializable

data class Search2(
    override val queryParams: Map<String, Serializable>
    ) : SubsonicRequest<Result2>() {

    override val path: RequestPath = RequestPath.SEARCH2

    override fun getClazz(): Class<out Result2> {
        return Result2::class.java
    }
}
