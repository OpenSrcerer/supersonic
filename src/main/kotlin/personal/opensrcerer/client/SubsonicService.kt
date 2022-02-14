package personal.opensrcerer.client

import okhttp3.OkHttpClient
import okhttp3.Request
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import personal.opensrcerer.requests.RequestUtils
import personal.opensrcerer.requests.subsonic.SubsonicRequest
import personal.opensrcerer.requests.subsonic.VoidRequest
import personal.opensrcerer.responses.subsonic.SubsonicMonoCallback
import personal.opensrcerer.responses.subsonic.SubsonicResponse
import reactor.core.publisher.Mono
import java.util.concurrent.TimeUnit
import java.util.logging.Level

object SubsonicService {
    private val logger: Logger = LoggerFactory.getLogger(OkHttpClient::class.java)
    private val client: OkHttpClient

    init {
        logger.debug("Initializing OkHttp singleton for subsonic servers...")
        client = OkHttpClient().newBuilder()
            .callTimeout(5000, TimeUnit.MILLISECONDS)
            .build()
    }

    fun request(req: VoidRequest, guildId: String): Mono<SubsonicResponse> {
        return Mono.create<SubsonicResponse> { monoSink ->
            client.newCall(
                Request.Builder()
                    .url(RequestUtils.getUrl(req, guildId))
                    .build()
            ).enqueue(SubsonicMonoCallback(monoSink, SubsonicResponse::class.java)) }
            .log("Subsonic Requests", Level.INFO)
    }

    fun <T> request(req: SubsonicRequest<T>, guildId: String): Mono<T>
    where T : SubsonicResponse {
        return Mono.create<T> { monoSink ->
            client.newCall(
                Request.Builder()
                    .url(RequestUtils.getUrl(req, guildId))
                    .build()
            ).enqueue(SubsonicMonoCallback(monoSink, req.getClazz())) }
            .log("Subsonic Requests", Level.INFO)
    }

    fun shutdown() {
        client.dispatcher.executorService.shutdown()
        client.connectionPool.evictAll()
        client.cache?.close()
    }
}