package personal.opensrcerer.requests

import okhttp3.HttpUrl
import personal.opensrcerer.client.cache.SubsonicCache
import personal.opensrcerer.client.cache.SubsonicConfig
import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.ThreadLocalRandom
import kotlin.streams.asSequence

object RequestFormatter {
    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    fun getUrl(type: RequestType, guildId: String): HttpUrl {
        // val passwordAndSalt = hashMd5("DNFWTF4201?!")
        val config: SubsonicConfig = SubsonicCache.get(guildId)!!
        return HttpUrl.Builder()
            .scheme("http")
            .host(config.host)
            .port(config.port)
            .addPathSegment("rest")
            .addPathSegment(type.path)
            .addQueryParameter("u", config.username)
            //.addQueryParameter("t", passwordAndSalt.first)
            //.addQueryParameter("s", passwordAndSalt.second)
            .addQueryParameter("p", config.password)
            .addQueryParameter("v", config.version)
            .addQueryParameter("c", "supersonic-bot")
            .build()
    }

    private fun hashMd5(password: String): Pair<String, String> {
        val md = MessageDigest.getInstance("MD5")
        val salt = getNextSalt()
        val saltedPassword = "$password$salt"
        return Pair(
            BigInteger(1, md.digest(
                saltedPassword.toByteArray())
            ).toString(16).padStart(32, '0'),
            salt
        )
    }

    private fun getNextSalt(): String {
        return ThreadLocalRandom.current()
            .ints(20L, 0, charPool.size)
            .asSequence()
            .map(charPool::get)
            .joinToString("")
    }
}