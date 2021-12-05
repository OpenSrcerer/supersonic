package personal.opensrcerer.messaging.interfaces.embedInterfaces

/**
 * Used as an adapter for paginated and cursorized entities.
 * @param T type of element that is Cursorized.
 * @param U Type of element that is Paginated.
 */
interface PaginatedCursorized<T, U> : Cursorized<T>, Paginated<U>