package personal.opensrcerer.client

import okhttp3.OkHttpClient
import okhttp3.Request
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import personal.opensrcerer.requests.RequestFormatter
import personal.opensrcerer.requests.RequestPath
import personal.opensrcerer.responses.SubsonicResponse
import personal.opensrcerer.responses.ResponseWrapper
import java.util.concurrent.TimeUnit

object SubsonicClient {

    private val logger: Logger = LoggerFactory.getLogger(OkHttpClient::class.java)
    private val client: OkHttpClient

    init {
        logger.debug("Initializing OkHttp singleton for subsonic servers...")
        client = OkHttpClient().newBuilder()
            .callTimeout(5000, TimeUnit.SECONDS)
            .build()
    }

    fun <T> request(target: Class<T>, type: RequestPath, guildId: String): ResponseWrapper<T>
    where T : SubsonicResponse<T> {
        val response = client.newCall(
            Request.Builder()
                .url(RequestFormatter.getUrl(type, guildId))
                .build()
        ).execute()

        val body = response.body?.string()!!

        return ResponseWrapper(target, body)
    }

    fun shutdown() {
        client.dispatcher.executorService.shutdown()
        client.connectionPool.evictAll()
        client.cache?.close()
    }
}