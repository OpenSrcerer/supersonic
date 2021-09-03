package personal.opensrcerer.client.cache

object SubsonicCache {
    private val cacheMap: MutableMap<String, SubsonicConfig>

    init {
        cacheMap = HashMap()
        cacheMap.put(
            "824772718800666645",
            SubsonicConfig(
                "",
                ,
                "",
                "?!",
                "1.15.0"
            )
        )
    }

    fun get(guildId: String): SubsonicConfig? {
        return cacheMap[guildId]
    }
}