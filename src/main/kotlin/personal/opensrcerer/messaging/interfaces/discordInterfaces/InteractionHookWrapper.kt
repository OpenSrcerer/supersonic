package personal.opensrcerer.messaging.interfaces.discordInterfaces

import net.dv8tion.jda.api.interactions.InteractionHook
import personal.opensrcerer.messaging.constant.ConstantEmbeds
import personal.opensrcerer.messaging.entities.EmbedEntity
import personal.opensrcerer.messaging.entities.Page
import personal.opensrcerer.messaging.impl.paginatedEmbeds.search.SearchEmbedActionRowTemplates
import personal.opensrcerer.messaging.interfaces.embedInterfaces.PaginatedCursorized
import java.util.function.Consumer

/**
 * Symbolizes a "window" to easily manage reactive embeds upon user request.
 */
class InteractionHookWrapper(private val embed: PaginatedCursorized<EmbedEntity, Page>,
                             private val hook: InteractionHook) {

    /**
     * Changes an embed locally and pushes changes to Discord.
     * @param embedConsumer Action to perform on the embed.
     */
    fun nav(embedConsumer: Consumer<
            PaginatedCursorized<EmbedEntity, Page>
            >
    ) {
        embedConsumer.accept(embed)
        val messageEmbed = embed.current()?.asMessageEmbed(embed.selectedRow())
        hook.editOriginalEmbeds(messageEmbed ?: ConstantEmbeds.noResults())
            .setActionRows(SearchEmbedActionRowTemplates.get(embed))
            .queue()
    }

    /**
     * @return True if provided user ID is owner of message.
     */
    fun isOwner(userId: String): Boolean {
        return hook.interaction.user.id == userId
    }

    /**
     * Deletes the original hook embed message.
     */
    fun expunge() {
        hook.deleteOriginal().queue()
    }
}
