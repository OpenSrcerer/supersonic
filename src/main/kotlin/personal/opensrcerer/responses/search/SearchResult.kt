package personal.opensrcerer.responses.search

import org.jetbrains.annotations.Nullable
import personal.opensrcerer.responses.entities.Album
import personal.opensrcerer.responses.entities.Artist
import personal.opensrcerer.responses.entities.Song

sealed interface SearchResult {
    @Nullable
    fun getArtists() : Array<Artist>?

    @Nullable
    fun getAlbums() : Array<Album>?

    @Nullable
    fun getSongs() : Array<Song>?
}