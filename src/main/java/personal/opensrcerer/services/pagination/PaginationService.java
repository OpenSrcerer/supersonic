package personal.opensrcerer.services.pagination;

import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import personal.opensrcerer.launch.SupersonicConstants;
import personal.opensrcerer.messaging.entities.EmbedEntity;
import personal.opensrcerer.messaging.entities.Page;
import personal.opensrcerer.messaging.impl.paginatedEmbeds.search.SearchEmbed;
import personal.opensrcerer.messaging.impl.paginatedEmbeds.search.SearchEmbedType;
import personal.opensrcerer.messaging.interfaces.discordInterfaces.InteractionHookWrapper;
import personal.opensrcerer.messaging.interfaces.embedInterfaces.Cursorized;
import personal.opensrcerer.messaging.interfaces.embedInterfaces.Paginated;
import personal.opensrcerer.messaging.interfaces.embedInterfaces.PaginatedCursorized;
import personal.opensrcerer.services.audio.AudioUtils;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Handles the management of PaginatedEmbeds for easy access from sinks.
 */
public class PaginationService {

    /**
     * Contains entries for accessible paginated messages.
     */
    private static final Map<String, InteractionHookWrapper> paginationMap;

    /**
     * Schedules expiration for map entries.
     */
    private static final ScheduledExecutorService ses;

    static {
        ses = Executors.newScheduledThreadPool(1, r ->
                new Thread(r, "Pagination-Manager")
        );
        paginationMap = new ConcurrentHashMap<>();
    }

    @FunctionalInterface
    interface EmbedAction<T, U> {
        /**
         * Pagination to perform on given embed.
         * @param paginatedCursorized Embed to change.
         */
        void perform(PaginatedCursorized<T, U> paginatedCursorized);
    }

    @SuppressWarnings("ConstantConditions")
    public static void decode(@Nonnull ButtonClickEvent event) {
        var wrapper = paginationMap.get(event.getMessageId());
        if (wrapper != null && wrapper.isOwner(event.getUser().getId())) {
            decode(event, wrapper, event.getMessageId(), ButtonAction.fromString(event.getButton().getId()));
        }
    }

    private static void decode(@Nonnull ButtonClickEvent event,
                               @Nonnull InteractionHookWrapper wrapper,
                               @Nonnull String messageId,
                               @Nonnull ButtonAction action) {
        switch (action) {
            /* Pagination */
            case FIRST -> navigate(event, wrapper, pc -> pc.previous(Integer.MAX_VALUE));
            case PREV -> navigate(event, wrapper, Paginated::previous);
            case NEXT -> navigate(event, wrapper, Paginated::next);
            case LAST -> navigate(event, wrapper, pc -> pc.next(Integer.MAX_VALUE));

            /* Cursorization */
            case UP -> navigate(event, wrapper, Cursorized::up);
            case SELECT -> wrapper.run(embed -> AudioUtils.playSongById(event, embed.select().embedName(), embed.select().id()));
            case DOWN -> navigate(event, wrapper, Cursorized::down);

            /* SearchEmbed Specific */
            case SONG -> navigate(event, wrapper, pc -> {
                if (pc instanceof SearchEmbed) ((SearchEmbed) pc).type(SearchEmbedType.SONG);
            });
            case ALBUM -> navigate(event, wrapper, pc -> {
                if (pc instanceof SearchEmbed) ((SearchEmbed) pc).type(SearchEmbedType.ALBUM);
            });
            case ARTIST -> navigate(event, wrapper, pc -> {
                if (pc instanceof SearchEmbed) ((SearchEmbed) pc).type(SearchEmbedType.ARTIST);
            });

            case DELETE -> removeNow(wrapper, messageId);
        }
    }

    /**
     * Move up or down using cursorization, or left or right using pagination,
     */
    public static void navigate(@Nonnull ButtonClickEvent event,
                                @Nonnull InteractionHookWrapper wrapper,
                                @Nonnull EmbedAction<EmbedEntity, Page> action) {
        wrapper.modify(action::perform);
        event.deferEdit().queue();
    }

    public static void add(@Nonnull String messageId,
                           @Nonnull PaginatedCursorized<EmbedEntity, Page> embed,
                           @Nonnull InteractionHook hook) {
        paginationMap.put(messageId, new InteractionHookWrapper(embed, hook));
        remove(messageId);
    }

    private static void remove(@Nonnull String messageId) {
        ses.schedule(() -> paginationMap.remove(messageId),
                SupersonicConstants.DEFAULT_PAGINATION_EMBED_LIFE_TIME_MINUTES,
                TimeUnit.MINUTES
        );
    }

    private static void removeNow(@Nonnull InteractionHookWrapper wrapper,
                                  @Nonnull String messageId) {
        wrapper.expunge();
        paginationMap.remove(messageId);
    }
}
