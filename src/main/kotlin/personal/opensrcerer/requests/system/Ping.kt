package personal.opensrcerer.requests.system

import personal.opensrcerer.requests.RequestPath
import personal.opensrcerer.requests.subsonic.VoidRequest

class Ping : VoidRequest() {
    override val path: RequestPath = RequestPath.PING
}
