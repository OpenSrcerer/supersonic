package personal.opensrcerer.messaging.impl.paginatedEmbeds.search

import net.dv8tion.jda.api.interactions.components.ActionRow
import net.dv8tion.jda.api.interactions.components.Button
import personal.opensrcerer.messaging.interfaces.embedInterfaces.PaginatedCursorized
import personal.opensrcerer.services.pagination.ButtonAction
import java.util.*

class SearchEmbedActionRowTemplates {
    companion object {
        /**
         * Returns an Array of ActionRow-s to use in the Discord
         * message for PaginatedCursorized embeds.
         */
        fun <T, U> get(embed: PaginatedCursorized<T, U>): List<ActionRow> {
            if (embed.isEmpty()) {
                return emptyList()
            }

            return when (embed) {
                is SearchEmbed -> {
                    return when (embed.currentType()) {
                        SearchEmbedType.ARTIST -> listOf(
                            defaultPaginationActionRow(),
                            defaultCursorizationActionRow(),
                            ActionRow.of(
                                Button.secondary(ButtonAction.SONG.id(), "Song"),
                                Button.secondary(UUID.randomUUID().toString(), " "),
                                Button.secondary(UUID.randomUUID().toString(), " "),
                                Button.secondary(UUID.randomUUID().toString(), " "),
                                Button.secondary(ButtonAction.ALBUM.id(), "Album")
                            )
                        )
                        SearchEmbedType.SONG -> listOf(
                            defaultPaginationActionRow(),
                            defaultCursorizationActionRow(),
                            ActionRow.of(
                                Button.secondary(ButtonAction.ARTIST.id(), "Artist"),
                                Button.secondary(UUID.randomUUID().toString(), " "),
                                Button.secondary(UUID.randomUUID().toString(), " "),
                                Button.secondary(UUID.randomUUID().toString(), " "),
                                Button.secondary(ButtonAction.ALBUM.id(), "Album")
                            )
                        )
                        SearchEmbedType.ALBUM -> listOf(
                            defaultPaginationActionRow(),
                            defaultCursorizationActionRow(),
                            ActionRow.of(
                                Button.secondary(ButtonAction.ARTIST.id(), "Artist"),
                                Button.secondary(UUID.randomUUID().toString(), " "),
                                Button.secondary(UUID.randomUUID().toString(), " "),
                                Button.secondary(UUID.randomUUID().toString(), " "),
                                Button.secondary(ButtonAction.SONG.id(), "Song"),
                            )
                        )
                    }
                }
                else -> listOf(defaultPaginationActionRow(), defaultCursorizationActionRow())
            }
        }

        private fun defaultCursorizationActionRow(): ActionRow {
            return ActionRow.of(
                Button.secondary(UUID.randomUUID().toString(), " "),
                Button.primary(ButtonAction.UP.id(), "⬆️"),
                Button.danger(ButtonAction.DELETE.id(), "\uD83D\uDDD1️"),
                Button.primary(ButtonAction.DOWN.id(), "⬇️"),
                Button.secondary(UUID.randomUUID().toString(), " ")
            )
        }

        private fun defaultPaginationActionRow(): ActionRow {
            return ActionRow.of(
                Button.primary(ButtonAction.FIRST.id(), "⏪"),
                Button.primary(ButtonAction.PREV.id(), "⬅️"),
                Button.primary(ButtonAction.SELECT.id(), "▶️"),
                Button.primary(ButtonAction.NEXT.id(), "➡️"),
                Button.primary(ButtonAction.LAST.id(), "⏩")
            )
        }
    }
}