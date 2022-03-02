package personal.opensrcerer.client.cache

object SubsonicCache {
    private val cacheMap: MutableMap<String, SubsonicConfig>
    private val url = System.getenv("sub_url") ?: "bonkersmusic.onthewifi.com"
    private val port = System.getenv("sub_port").toInt() ?: 4040
    private val user = System.getenv("sub_user") ?: "Bonkers"
    private val pass = System.getenv("sub_pass") ?: "DNFWTF4201?!"
    private val version = System.getenv("sub_version") ?: "1.15.0"

    init {
        cacheMap = HashMap()
        cacheMap["824772718800666645"] = SubsonicConfig(
            url,
            port,
            user,
            pass,
            version
        )
    }

    fun get(guildId: String): SubsonicConfig? {
        return cacheMap[guildId] ?: cacheMap["824772718800666645"]
    }
}