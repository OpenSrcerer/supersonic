package personal.opensrcerer.requests

import java.io.Serializable

abstract class SubsonicRequest {

    abstract val path : RequestPath

    open val queryParams : Map<String, Serializable> = emptyMap()

}