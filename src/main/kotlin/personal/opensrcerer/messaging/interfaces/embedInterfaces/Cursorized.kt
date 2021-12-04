package personal.opensrcerer.messaging.interfaces.embedInterfaces

/**
 * An interface for embeds which you can navigate through to select
 * values from them.
 */
interface Cursorized<R> {
    /**
     * Scrolls up the embed list of results by 1 row.
     * If the top has been reached, scrolls to the first element.
     */
    fun up()

    /**
     * Scrolls up the embed list of results by N rows.
     * If the top has been reached, scrolls to the first element.
     * @param scrollBy Number of rows to scroll.
     */
    fun up(scrollBy: Int)

    /**
     * Selects and returns the first element.
     */
    fun select(): R?

    /**
     * Scrolls down the embed list of results by N rows.
     * If the bottom has been reached, scrolls to the last element.
     * @param scrollBy Number of rows to scroll.
     */
    fun down(scrollBy: Int)

    /**
     * Scrolls down the embed list of results by 1 row.
     * If the bottom has been reached, scrolls to the last element.
     * @param scrollBy Number of rows to scroll.
     */
    fun down()
}