package personal.opensrcerer.client.cache
import personal.opensrcerer.launch.SupersonicConstants

object SubsonicCache {
    private val cacheMap: MutableMap<String, SubsonicConfig>
    private val url = SupersonicConstants.getVariable("SUB_URL")!! ?: "bonkersmusic.onthewifi.com"
    private val port = SupersonicConstants.getVariable("SUB_PORT")?.toInt()!! ?: 4040
    private val user = SupersonicConstants.getVariable("SUB_USER")!! ?: "Bonkers"
    private val pass = SupersonicConstants.getVariable("SUB_PASS")!! ?: "DNFWTF4201?!"
    private val version =  SupersonicConstants.getVariable("SUB_VERSION")!! ?: "1.15.0"

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
