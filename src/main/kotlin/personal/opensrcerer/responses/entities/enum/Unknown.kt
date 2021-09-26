package personal.opensrcerer.responses.entities.enum

enum class Unknown(val value: String) {
    ID("Unknown ID"),
    PARENT("Unknown Parent"),
    TITLE("Unknown Title"),
    IS_DIRECTORY("Unknown if Directory"),
    IS_VIDEO("Unknown if Video"),
    ALBUM("Unknown Album"),
    ARTIST("Unknown Artist"),
    ARTIST_NAME("Unknown Artist Name"),
    TRACK("Unknown Track"),
    YEAR("Unknown Year"),
    GENRE("Unknown Genre"),
    COVER_ART("Unknown Cover Art"),
    SIZE("Unknown Size"),
    CONTENT_TYPE("Unknown Content Type"),
    SUFFIX("Unknown Suffix"),
    TRANSCODED_CONTENT_TYPE("Unknown Transcoded Content Type"),
    TRANSCODED_SUFFIX("Unknown Transcoded Suffix"),
    DURATION("Unknown Duration"),
    BITRATE("Unknown Bitrate"),
    PATH("Unknown Path");

    override fun toString(): String {
        return value
    }
}