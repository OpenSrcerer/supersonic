package personal.opensrcerer.client.cache

import personal.opensrcerer.launch.SupersonicConstants

object SubsonicCache {
    private val cacheMap: MutableMap<String, SubsonicConfig>

    init {
        cacheMap = HashMap()
        cacheMap["824772718800666645"] = SubsonicConfig(
            SupersonicConstants.getVariable("SUB_URL") ?: "bonkersmusic.onthewifi.com",
            SupersonicConstants.getVariable("SUB_PORT")?.toInt() ?: 4040,
            SupersonicConstants.getVariable("SUB_USER") ?: "Bonkers",
            SupersonicConstants.getVariable("SUB_PASS") ?: "DNFWTF4201?!",
            SupersonicConstants.getVariable("SUB_VERSION") ?: "1.15.0"
        )
    }

    fun get(guildId: String): SubsonicConfig? {
        return cacheMap[guildId] ?: cacheMap["824772718800666645"]
    }
}
