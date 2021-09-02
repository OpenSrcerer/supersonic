package personal.opensrcerer.client

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import personal.opensrcerer.requests.RequestFormatter
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

    fun request(): Response {
        return client.newCall(
            Request.Builder()
                .url(RequestFormatter.getUrl())
                .header("e", "e")
                .build()
        ).execute()
    }

    fun shutdown() {
        client.dispatcher.executorService.shutdown()
        client.connectionPool.evictAll()
        client.cache?.close()
    }
}