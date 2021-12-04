package personal.opensrcerer.messaging.interfaces.discordInterfaces

import net.dv8tion.jda.api.interactions.InteractionHook
import personal.opensrcerer.messaging.impl.paginatedEmbeds.ReactiveEmbed
import java.util.function.Consumer

/**
 * Symbolizes a "window" to easily manage reactive embeds upon user request.
 */
class InteractionHookWrapper(private val embed: ReactiveEmbed,
                             private val hook: InteractionHook) {

    /**
     * Changes an embed locally and pushes changes to Discord.
     * @param embedConsumer Action to perform on the embed.
     */
    fun nav(embedConsumer: Consumer<ReactiveEmbed>) {
        embedConsumer.accept(embed)
        hook.editOriginalEmbeds(embed.current()?.asMessageEmbed())
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
