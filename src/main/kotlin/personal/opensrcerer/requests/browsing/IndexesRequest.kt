package personal.opensrcerer.requests.browsing

import personal.opensrcerer.requests.RequestPath
import personal.opensrcerer.requests.SubsonicRequest
import personal.opensrcerer.responses.browsing.Indexes

class IndexesRequest: SubsonicRequest<Indexes>() {
    override val path: RequestPath = RequestPath.GET_INDEXES

    override fun getClazz(): Class<out Indexes> {
        return Indexes::class.java
    }
}