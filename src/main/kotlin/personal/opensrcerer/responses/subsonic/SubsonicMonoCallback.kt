package personal.opensrcerer.responses.subsonic

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import reactor.core.publisher.MonoSink
import java.io.IOException
import java.lang.Exception
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.TimeUnit

class SubsonicMonoCallback<T>(
    private val sink: MonoSink<T>,
    private val clazz: Class<out T>
    ) : Callback
    where T : SubsonicResponse {
    override fun onResponse(call: Call, response: Response) {
        val body = response.body?.string()

        println(body)

        if (body.isNullOrEmpty()) {
            onFailure(call, IOException("Response body sent by the Subsonic Server was null."))
            return
        }

        try {
            val res = parse(clazz, body)
            res.setTime(ChronoUnit.MILLIS.between(
                Instant.ofEpochMilli(response.sentRequestAtMillis),
                Instant.ofEpochMilli(response.receivedResponseAtMillis)
            ))
            sink.success(res)
        } catch (e: Exception) {
            onFailure(call, IOException(e.message))
        }
    }

    override fun onFailure(call: Call, e: IOException) {
        sink.error(e)
    }

    private companion object {
        private val xmlMapper = XmlMapper()

        fun <T> parse(
            clazz: Class<T>,
            xml: String
        ) : T {
            return xmlMapper.readValue(xml, clazz)
        }
    }
}