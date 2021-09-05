package personal.opensrcerer.responses.search

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import personal.opensrcerer.responses.SubsonicResponse
import personal.opensrcerer.responses.entities.Album
import personal.opensrcerer.responses.entities.Artist
import personal.opensrcerer.responses.entities.Song

class Result3 : SubsonicResponse<Result3>(), SearchResult {

    private val searchResult3 = SearchResult3()

    private class SearchResult3 {
        @JsonIgnoreProperties(ignoreUnknown = true)
        @JacksonXmlElementWrapper(useWrapping = false)
        val artist: Array<Artist>? = null

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JacksonXmlElementWrapper(useWrapping = false)
        val album: Array<Album>? = null

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JacksonXmlElementWrapper(useWrapping = false)
        val song: Array<Song>? = null
    }

    override fun getArtists() : Array<Artist>? {
        return this.searchResult3.artist
    }

    override fun getAlbums() : Array<Album>? {
        return this.searchResult3.album
    }

    override fun getSongs() : Array<Song>? {
        return this.searchResult3.song
    }
}