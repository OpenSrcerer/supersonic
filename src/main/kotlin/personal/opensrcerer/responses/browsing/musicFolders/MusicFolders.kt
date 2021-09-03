package personal.opensrcerer.responses.browsing.musicFolders

import personal.opensrcerer.responses.SubsonicData

data class MusicFolders(
    val musicFolders: Array<MusicFolder>
) : SubsonicData {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MusicFolders

        if (!musicFolders.contentEquals(other.musicFolders)) return false

        return true
    }

    override fun hashCode(): Int {
        return musicFolders.contentHashCode()
    }
}