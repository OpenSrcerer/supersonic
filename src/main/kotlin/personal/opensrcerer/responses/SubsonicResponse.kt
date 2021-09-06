package personal.opensrcerer.responses

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

abstract class SubsonicResponse {
    val status: String = ""
    val version: String = ""
    val xmlns: String = ""
    val error: SubsonicError? = null

    class SubsonicError @JsonCreator constructor(
        @JsonProperty("code") val code: Int,
        @JsonProperty("message") val message: String
    )
}