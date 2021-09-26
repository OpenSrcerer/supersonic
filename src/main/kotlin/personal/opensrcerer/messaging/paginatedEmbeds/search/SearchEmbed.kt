package personal.opensrcerer.messaging.paginatedEmbeds.search

import personal.opensrcerer.responses.entities.EmbedMusicEntity
import personal.opensrcerer.responses.search.Result3
import java.util.*

class SearchEmbed(results: List<SearchEmbedResult>) {
    private val map: EnumMap<SearchEmbedType, SearchEmbedResult> = EnumMap(SearchEmbedType::class.java)

    init {
        results.forEach { e -> map[e.type] = e }
    }

    constructor(result: Result3) : this(
        listOf(
            SearchEmbedResult(SearchEmbedType.ALBUM, result.getAlbums() ?: emptyArray<EmbedMusicEntity>()),
            SearchEmbedResult(SearchEmbedType.ARTIST, result.getArtists() ?: emptyArray<EmbedMusicEntity>()),
            SearchEmbedResult(SearchEmbedType.SONG, result.getSongs() ?: emptyArray<EmbedMusicEntity>())
        )
    )

    fun getEmbed(type: SearchEmbedType): SearchEmbedResult? {
        return map[type]
    }
}