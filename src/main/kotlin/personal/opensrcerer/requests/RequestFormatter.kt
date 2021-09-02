package personal.opensrcerer.requests

import okhttp3.HttpUrl
import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.ThreadLocalRandom
import kotlin.streams.asSequence

object RequestFormatter {
    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    fun getUrl(): HttpUrl {
        val passwordAndSalt = hashMd5("")
        val e = HttpUrl.Builder()
            .scheme("http")
            .host("")
            .port(1)
            .addPathSegment("rest")
            .addPathSegment("ping.view")
            .addQueryParameter("u", "")
            .addQueryParameter("t", passwordAndSalt.first)
            .addQueryParameter("s", passwordAndSalt.second)
            .addQueryParameter("v", "1.15.0")
            .addQueryParameter("c", "supersonic-bot")
            .build()
        println(e)
        return e
    }

    private fun hashMd5(password: String): Pair<String, String> {
        val md = MessageDigest.getInstance("MD5")
        val salt = getNextSalt()
        val saltedPassword = "$password$salt"
        println(saltedPassword)
        return Pair(BigInteger(1, md.digest(saltedPassword.toByteArray())).toString(16).padStart(32, '0'), salt)
    }

    private fun getNextSalt(): String {
        return ThreadLocalRandom.current()
            .ints(20L, 0, charPool.size)
            .asSequence()
            .map(charPool::get)
            .joinToString("")
    }
}