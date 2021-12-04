package personal.opensrcerer.services.pagination;

public enum ButtonAction {

    /* Pagination */
    FIRST("first"),
    PREV("prev"),
    NEXT("next"),
    LAST("last"),

    /* Cursorization */
    UP("up"),
    SELECT("select"),
    DOWN("down"),

    /* Search Embed Results */
    SONG("song"),
    ALBUM("album"),
    ARTIST("artist"),

    DELETE("delete");

    private final String buttonId;

    ButtonAction(String buttonId) {
        this.buttonId = buttonId;
    }

    public static ButtonAction fromString(String text) {
        for (ButtonAction b : ButtonAction.values()) {
            if (b.buttonId.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return ButtonAction.NEXT;
    }
}
