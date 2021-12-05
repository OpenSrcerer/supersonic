package personal.opensrcerer.services.pagination;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.InteractionHook;
import personal.opensrcerer.launch.SupersonicConstants;
import personal.opensrcerer.messaging.entities.EmbedEntity;
import personal.opensrcerer.messaging.entities.Page;
import personal.opensrcerer.messaging.impl.paginatedEmbeds.ReactiveEmbed;
import personal.opensrcerer.messaging.interfaces.discordInterfaces.InteractionHookWrapper;
import personal.opensrcerer.messaging.interfaces.embedInterfaces.Cursorized;
import personal.opensrcerer.messaging.interfaces.embedInterfaces.Paginated;
import personal.opensrcerer.messaging.impl.paginatedEmbeds.search.SearchEmbed;
import personal.opensrcerer.messaging.impl.paginatedEmbeds.search.SearchEmbedType;
import personal.opensrcerer.messaging.interfaces.embedInterfaces.PaginatedCursorized;

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
    interface EmbedAction {
        /**
         * Pagination to perform on given embed.
         * @param paginatedCursorized Embed to change.
         */
        void perform(PaginatedCursorized<EmbedEntity, Page> paginatedCursorized);
    }

    public static void decode(@Nonnull String userId, @Nonnull String messageId, String action) {
        var wrapper = paginationMap.get(messageId);
        if (wrapper != null && wrapper.isOwner(userId)) {
            decode(wrapper, messageId, ButtonAction.fromString(action));
        }
    }

    private static void decode(@Nonnull InteractionHookWrapper wrapper,
                               @Nonnull String messageId,
                               @Nonnull ButtonAction action) {
        switch (action) {
            /* Pagination */
            case FIRST -> navigate(wrapper, e -> e.previous(Integer.MAX_VALUE));
            case PREV -> navigate(wrapper, Paginated::previous);
            case NEXT -> navigate(wrapper, Paginated::next);
            case LAST -> navigate(wrapper, e -> e.next(Integer.MAX_VALUE));

            /* Cursorization */
            case UP -> navigate(wrapper, Cursorized::up);
            case SELECT -> navigate(wrapper, e -> e.select());
            case DOWN -> navigate(wrapper, Cursorized::down);

            /* SearchEmbed Specific */
            case SONG -> navigate(wrapper, e -> {
                if (e instanceof SearchEmbed) ((SearchEmbed) e).type(SearchEmbedType.SONG);
            });
            case ALBUM -> navigate(wrapper, e -> {
                if (e instanceof SearchEmbed) ((SearchEmbed) e).type(SearchEmbedType.ALBUM);
            });
            case ARTIST -> navigate(wrapper, e -> {
                if (e instanceof SearchEmbed) ((SearchEmbed) e).type(SearchEmbedType.ARTIST);
            });

            case DELETE -> removeNow(wrapper, messageId);
        }
    }

    public static void navigate(@Nonnull InteractionHookWrapper wrapper,
                                @Nonnull EmbedAction action) {
        wrapper.nav(action::perform);
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
