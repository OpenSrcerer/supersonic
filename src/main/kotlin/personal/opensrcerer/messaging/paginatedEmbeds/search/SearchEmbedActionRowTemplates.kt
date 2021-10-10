package personal.opensrcerer.messaging.paginatedEmbeds.search

import net.dv8tion.jda.api.interactions.components.ActionRow
import net.dv8tion.jda.api.interactions.components.Button
import personal.opensrcerer.messaging.paginatedEmbeds.PaginatedEmbed

class SearchEmbedActionRowTemplates {
    companion object {
        fun get(embed: PaginatedEmbed): List<ActionRow> {
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
                Button.primary("prev", "\uD83E\uDC44"),
                Button.primary("next", "\uD83E\uDC46"),
                Button.primary("last", "⏩"),
                Button.danger("delete", "\uD83D\uDDD1️")
            )
        }
    }
}