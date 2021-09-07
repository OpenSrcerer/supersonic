package personal.opensrcerer.requests.system

import personal.opensrcerer.requests.RequestPath
import personal.opensrcerer.requests.SubsonicRequest
import personal.opensrcerer.requests.VoidRequest

class Ping : VoidRequest() {
    override val path: RequestPath = RequestPath.PING
}
