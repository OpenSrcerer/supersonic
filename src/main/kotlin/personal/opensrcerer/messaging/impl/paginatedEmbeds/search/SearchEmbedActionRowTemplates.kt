package personal.opensrcerer.messaging.impl.paginatedEmbeds.search

import net.dv8tion.jda.api.interactions.components.ActionRow
import net.dv8tion.jda.api.interactions.components.Button
import personal.opensrcerer.messaging.interfaces.embedInterfaces.PaginatedEmbed

class SearchEmbedActionRowTemplates {
    companion object {
        fun <R> get(embed: PaginatedEmbed<R>): List<ActionRow> {
            return when (embed) {
                is SearchEmbed -> {
                    return when (embed.currentType()) {
                        SearchEmbedType.ARTIST -> listOf(
                            defaultPaginationActionRow(),
                            ActionRow.of(
                                Button.secondary("song", "Songs"),
                                Button.secondary("album", "Albums")
                            )
                        )
                        SearchEmbedType.SONG -> listOf(
                            defaultPaginationActionRow(),
                            ActionRow.of(
                                Button.secondary("artist", "Artists"),
                                Button.secondary("album", "Albums")
                            )
                        )
                        SearchEmbedType.ALBUM -> listOf(
                            defaultPaginationActionRow(),
                            ActionRow.of(
                                Button.secondary("artist", "Artists"),
                                Button.secondary("song", "Songs")
                            )
                        )
                    }
                }
                else -> listOf(defaultPaginationActionRow())
            }
        }

        private fun defaultPaginationActionRow(): ActionRow {
            return ActionRow.of(
                Button.primary("first", "⏪"),
                Button.primary("prev", "◀"),
                Button.primary("next", "▶"),
                Button.primary("last", "⏩"),
                Button.danger("delete", "\uD83D\uDDD1️")
            )
        }
    }
}