package personal.opensrcerer.requests.system

import personal.opensrcerer.requests.RequestPath
import personal.opensrcerer.requests.SubsonicRequest

class Ping : SubsonicRequest() {
    override val path: RequestPath = RequestPath.PING
}
