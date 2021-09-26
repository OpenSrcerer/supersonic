package personal.opensrcerer.responses.subsonic

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

open class SubsonicResponse {
    val status: String = ""
    val version: String = ""
    val xmlns: String = ""
    val error: SubsonicError? = null
    private var requestTime: Long = 0

    class SubsonicError @JsonCreator constructor(
        @JsonProperty("code") val code: Int,
        @JsonProperty("message") val message: String
    )

    fun setTime(t: Long) {
        requestTime = t
    }

    fun getTime() : Long {
        return requestTime
    }
}