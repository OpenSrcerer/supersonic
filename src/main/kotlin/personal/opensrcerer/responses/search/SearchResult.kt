package personal.opensrcerer.responses.search

import personal.opensrcerer.responses.entities.Album
import personal.opensrcerer.responses.entities.Artist
import personal.opensrcerer.responses.entities.Song

sealed interface SearchResult {
    fun getArtists() : Array<Artist>?

    fun getAlbums() : Array<Album>?

    fun getSongs() : Array<Song>?
}