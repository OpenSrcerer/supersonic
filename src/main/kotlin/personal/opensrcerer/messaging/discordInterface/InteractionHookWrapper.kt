package personal.opensrcerer.messaging.discordInterface

import net.dv8tion.jda.api.interactions.InteractionHook
import personal.opensrcerer.messaging.paginatedEmbeds.PaginatedEmbed
import java.util.function.Consumer

/**
 * Symbolizes a "window" to easily page paginated embeds upon user request.
 */
class InteractionHookWrapper(private val embed: PaginatedEmbed,
                             private val hook: InteractionHook) {

    /**
     * Changes an embed locally and pushes changes to Discord.
     * @param paginatedEmbedConsumer Action to perform on the embed.
     */
    fun alter(paginatedEmbedConsumer: Consumer<PaginatedEmbed>) {
        paginatedEmbedConsumer.accept(embed)
        hook.editOriginalEmbeds(embed.current())
            .setActionRows(embed.getRows())
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
