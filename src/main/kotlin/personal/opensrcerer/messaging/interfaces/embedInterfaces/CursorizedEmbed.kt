package personal.opensrcerer.messaging.interfaces.embedInterfaces

import personal.opensrcerer.messaging.entities.EmbedEntity

/**
 * An interface for embeds which you can navigate through to select
 * values from them.
 */
interface CursorizedEmbed {
    /**
     * Scrolls up the embed list of results by N rows.
     * If the top has been reached, scrolls to the first element.
     * @param scrollBy Number of rows to scroll.
     */
    fun up(scrollBy: Int)

    /**
     * Scrolls down the embed list of results by N rows.
     * If the bottom has been reached, scrolls to the last element.
     * @param scrollBy Number of rows to scroll.
     */
    fun down(scrollBy: Int)

    /**
     * Selects and returns the first element.
     */
    fun select(): EmbedEntity
}