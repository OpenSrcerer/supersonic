package personal.opensrcerer.services.pagination;

import net.dv8tion.jda.api.interactions.InteractionHook;
import personal.opensrcerer.launch.SupersonicConstants;
import personal.opensrcerer.messaging.interfaces.discordInterfaces.InteractionHookWrapper;
import personal.opensrcerer.messaging.interfaces.embedInterfaces.PaginatedEmbed;
import personal.opensrcerer.messaging.impl.paginatedEmbeds.search.SearchEmbed;
import personal.opensrcerer.messaging.impl.paginatedEmbeds.search.SearchEmbedType;

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
    interface PaginationServiceAction {
        /**
         * Action to perform on given embed.
         * @param embed Embed to change.
         */
        void perform(PaginatedEmbed embed);
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
            case FIRST -> alter(wrapper, e -> e.previous(Integer.MAX_VALUE));
            case PREV -> alter(wrapper, PaginatedEmbed::previous);
            case NEXT -> alter(wrapper, PaginatedEmbed::next);
            case LAST -> alter(wrapper, e -> e.next(Integer.MAX_VALUE));
            case DELETE -> removeNow(wrapper, messageId);

            /* SearchEmbed Specific */
            case SONG -> alter(wrapper, e -> {
                if (e instanceof SearchEmbed) ((SearchEmbed) e).type(SearchEmbedType.SONG);
            });
            case ALBUM -> alter(wrapper, e -> {
                if (e instanceof SearchEmbed) ((SearchEmbed) e).type(SearchEmbedType.ALBUM);
            });
            case ARTIST -> alter(wrapper, e -> {
                if (e instanceof SearchEmbed) ((SearchEmbed) e).type(SearchEmbedType.ARTIST);
            });
        }
    }

    public static void alter(@Nonnull InteractionHookWrapper wrapper,
                             @Nonnull PaginationServiceAction action) {
        wrapper.alter(action::perform);
    }

    public static void add(@Nonnull String messageId,
                           @Nonnull PaginatedEmbed embed,
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
