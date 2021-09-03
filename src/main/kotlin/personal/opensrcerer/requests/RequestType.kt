package personal.opensrcerer.requests

import personal.opensrcerer.responses.SubsonicData
import personal.opensrcerer.responses.browsing.musicFolders.MusicFolders

enum class RequestType(
    val path: String,
    val clazz: Class<SubsonicData>
    )
{

    // System
    PING("ping", MusicFolders::class.java),

    // Browsing
    GET_MUSIC_FOLDERS   ("getMusicFolders", MusicFolders::class.java),
    GET_INDEXES         ("getIndexes", MusicFolders::class.java),
    GET_MUSIC_DIRECTORY ("getMusicDirectory", MusicFolders::class.java),
    GET_GENRES          ("getGenres", MusicFolders::class.java),
    GET_ARTISTS         ("getArtists", MusicFolders::class.java),
    GET_ARTIST          ("getArtist", MusicFolders::class.java),
    GET_ALBUM           ("getAlbum", MusicFolders::class.java),
    GET_SONG            ("getSong", MusicFolders::class.java),
    GET_VIDEOS          ("getVideos", MusicFolders::class.java),
    GET_VIDEO_INFO      ("getVideoInfo", MusicFolders::class.java),
    GET_ARTIST_INFO     ("getArtistInfo", MusicFolders::class.java),
    GET_ARTIST_INFO2    ("getArtistInfo2", MusicFolders::class.java),
    GET_ALBUM_INFO      ("getAlbumInfo", MusicFolders::class.java),
    GET_ALBUM_INFO2     ("getAlbumInfo2", MusicFolders::class.java),
    GET_SIMILAR_SONGS   ("getSimilarSongs", MusicFolders::class.java),
    GET_SIMILAR_SONGS2  ("getSimilarSongs2", MusicFolders::class.java),

    //
    GET_TOP_SONGS       ("getTopSongs", MusicFolders::class.java),
    GET_ALBUM_LIST      ("getAlbumList", MusicFolders::class.java),
    GET_ALBUM_LIST2     ("getAlbumList2", MusicFolders::class.java),
    GET_RANDOM_SONGS    ("getRandomSongs", MusicFolders::class.java),
    GET_SONGS_BY_GENRE  ("getSongsByGenre", MusicFolders::class.java),
    GET_NOW_PLAYING     ("getNowPlaying", MusicFolders::class.java),
    GET_STARRED         ("getStarred", MusicFolders::class.java),
    GET_STARRED2        ("getStarred2", MusicFolders::class.java),

    SEARCH              ("search", MusicFolders::class.java),
    SEARCH2             ("search2", MusicFolders::class.java),
    SEARCH3             ("search3", MusicFolders::class.java)
}