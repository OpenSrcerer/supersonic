package personal.opensrcerer.client

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import personal.opensrcerer.requests.RequestFormatter
import personal.opensrcerer.requests.SubsonicRequest
import personal.opensrcerer.requests.VoidRequest
import personal.opensrcerer.requests.media.StreamRequest
import personal.opensrcerer.responses.SubsonicResponse
import reactor.core.publisher.Mono
import java.io.InputStream
import java.util.concurrent.TimeUnit

object SubsonicClient {

    private val xmlMapper = XmlMapper()
    private val logger: Logger = LoggerFactory.getLogger(OkHttpClient::class.java)
    private val client: OkHttpClient

    init {
        logger.debug("Initializing OkHttp singleton for subsonic servers...")
        client = OkHttpClient().newBuilder()
            .callTimeout(5000, TimeUnit.SECONDS)
            .build()
    }

    fun stream(req: StreamRequest, guildId: String) : Mono<InputStream?> {
        val response = client.newCall(
            Request.Builder()
                .url(RequestFormatter.getUrl(req, guildId))
                .build()
        ).execute()

        val type = response.header("content-type")
        if (type.equals("text/xml")) {
            return Mono.empty()
        }

        return Mono.justOrEmpty(response.body?.byteStream()).share()
    }

    fun request(req: VoidRequest, guildId: String): SubsonicResponse {
        val response = client.newCall(
            Request.Builder()
                .url(RequestFormatter.getUrl(req, guildId))
                .build()
        ).execute()

        val body = response.body?.string()!!
        logger.debug(body)

        return this.parse(SubsonicResponse::class.java, body)
    }

    fun <T> request(req: SubsonicRequest<T>, guildId: String): T {
        val response = client.newCall(
            Request.Builder()
                .url(RequestFormatter.getUrl(req, guildId))
                .build()
        ).execute()

        val body = response.body?.string()!!
        logger.debug(body)

        return this.parse(req.getClazz(), body)
    }

    fun shutdown() {
        client.dispatcher.executorService.shutdown()
        client.connectionPool.evictAll()
        client.cache?.close()
    }

    private fun <T> parse(
        clazz: Class<T>,
        xml: String
    ) : T {
        return xmlMapper.readValue(xml, clazz)
    }
}