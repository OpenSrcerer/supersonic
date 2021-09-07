package personal.opensrcerer.requests.browsing

import personal.opensrcerer.requests.RequestPath
import personal.opensrcerer.requests.SubsonicRequest
import personal.opensrcerer.responses.browsing.musicFolders.MusicFolders

class MusicFoldersRequest : SubsonicRequest<MusicFolders>() {
    override val path: RequestPath = RequestPath.GET_MUSIC_FOLDERS

    override fun getClazz(): Class<out MusicFolders> {
        return MusicFolders::class.java
    }
}
