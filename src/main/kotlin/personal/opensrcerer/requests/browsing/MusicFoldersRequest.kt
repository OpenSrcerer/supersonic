package personal.opensrcerer.requests.browsing

import personal.opensrcerer.requests.RequestPath
import personal.opensrcerer.requests.SubsonicRequest

class MusicFoldersRequest : SubsonicRequest() {
    override val path: RequestPath = RequestPath.GET_MUSIC_FOLDERS
}
