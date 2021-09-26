package personal.opensrcerer.messaging.paginatedEmbeds

import net.dv8tion.jda.api.entities.MessageEmbed

interface PaginatedEmbed {
    /**
     * Get a page by its number.
     * @param pageNumber Page number where 1 is the first page.
     * @return The page with the corresponding number.
     * The first/last page if the number is out of the bounds of the result list.
     */
    fun getPage(pageNumber: Int): MessageEmbed?

    /**
     * Skips N pages back. If given a number larger than
     * the number of pages, stops at the first page.
     * @param skip Positive integer (number of pages to skip).
     * @return The current page after skipping.
     */
    fun previous(skip: Int): MessageEmbed?

    /**
     * Goes one page back.
     * @return The previous page, or the first page if there is no previous page.
     */
    fun previous(): MessageEmbed?

    /**
     * @return The current page.
     */
    fun current(): MessageEmbed?

    /**
     * Goes to the next page.
     * @return The next page, or the last page if there is no next page.
     */
    fun next(): MessageEmbed?

    /**
     * Goes forward by the N given pages. If given a number larger than
     * the number of pages, stops at the last page.
     * @param skip Positive integer (number of pages to skip).
     * @return The current page after skipping.
     */
    fun next(skip: Int): MessageEmbed?
}