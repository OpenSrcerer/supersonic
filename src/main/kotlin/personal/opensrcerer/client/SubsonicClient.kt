package personal.opensrcerer.client

import okhttp3.OkHttpClient
import okhttp3.Request
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import personal.opensrcerer.requests.RequestFormatter
import personal.opensrcerer.requests.RequestType
import personal.opensrcerer.responses.SubsonicData
import personal.opensrcerer.responses.SubsonicResponse
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

    fun <T> request(type: RequestType, guildId: String): SubsonicResponse<T>
    where T : SubsonicData {
        val response = client.newCall(
            Request.Builder()
                .url(RequestFormatter.getUrl(type, guildId))
                .build()
        ).execute()

        val body = response.body?.string()!!
        val status = response.code

        return SubsonicResponse(type.clazz, body, status)
    }

    fun shutdown() {
        client.dispatcher.executorService.shutdown()
        client.connectionPool.evictAll()
        client.cache?.close()
    }
}