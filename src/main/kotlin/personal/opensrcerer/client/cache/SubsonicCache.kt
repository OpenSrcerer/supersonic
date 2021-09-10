package personal.opensrcerer.client.cache

object SubsonicCache {
    private val cacheMap: MutableMap<String, SubsonicConfig>

    init {
        cacheMap = HashMap()
        cacheMap["824772718800666645"] = SubsonicConfig(
            "bonkersmusic.onthewifi.com",
            4040,
            "Bonkers",
            "DNFWTF4201?!",
            "1.15.0"
        )
    }

    fun get(guildId: String): SubsonicConfig? {
        return cacheMap[guildId]
    }
}