package personal.opensrcerer.messaging.impl.paginatedEmbeds.search

import personal.opensrcerer.launch.SupersonicConstants
import personal.opensrcerer.messaging.entities.EmbedEntity
import personal.opensrcerer.messaging.entities.Page
import personal.opensrcerer.messaging.interfaces.embedInterfaces.PaginatedCursorized
import personal.opensrcerer.responses.search.Result3
import java.util.*

class SearchEmbed(results: List<SearchEmbedResult>) : PaginatedCursorized<EmbedEntity, Page> {
    private var currentType: SearchEmbedType = SupersonicConstants.DEFAULT_SEARCH_EMBED_TYPE
    private val map: EnumMap<SearchEmbedType, SearchEmbedResult> = EnumMap(SearchEmbedType::class.java)

    init {
        results.forEach { e -> map[e.type] = e }
    }

    constructor(result: Result3) : this(
        listOf(
            SearchEmbedResult(SearchEmbedType.ALBUM, result.getAlbums() ?: emptyArray<EmbedEntity>()),
            SearchEmbedResult(SearchEmbedType.ARTIST, result.getArtists() ?: emptyArray<EmbedEntity>()),
            SearchEmbedResult(SearchEmbedType.SONG,
                result.getSongs()?.filter { s -> s.isVideo != "true" }?.toTypedArray() ?:
                emptyArray<EmbedEntity>()
            )
        )
    )

    fun type(type: SearchEmbedType) {
        currentType = type
    }

    fun currentRow(): Int? {
        return map[currentType]?.selectedRow()
    }

    fun currentType(): SearchEmbedType {
        return currentType
    }

    override fun getPage(pageNumber: Int): Page? {
        return map[currentType]?.getPage(pageNumber)
    }

    override fun previous(skip: Int): Page? {
        return map[currentType]?.previous(skip)
    }

    override fun previous(): Page? {
        return map[currentType]?.previous()
    }

    override fun current(): Page? {
        return map[currentType]?.current()
    }

    override fun next(): Page? {
        return map[currentType]?.next()
    }

    override fun next(skip: Int): Page? {
        return map[currentType]?.next(skip)
    }

    override fun selectedRow(): Int {
        return map[currentType]!!.selectedRow()
    }

    override fun up() {
        map[currentType]?.up()
    }

    override fun up(scrollBy: Int) {
        map[currentType]?.up(scrollBy)
    }

    override fun select(): EmbedEntity? {
        return map[currentType]?.select()
    }

    override fun down(scrollBy: Int) {
        map[currentType]?.down(scrollBy)
    }

    override fun down() {
        map[currentType]?.down()
    }
}