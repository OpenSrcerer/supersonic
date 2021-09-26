package personal.opensrcerer.requests.subsonic

abstract class VoidRequest : SubsonicRequest<Void>() {
    override fun getClazz(): Class<out Void> {
        return Void::class.java
    }
}