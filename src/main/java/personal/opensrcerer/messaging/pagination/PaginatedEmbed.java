package personal.opensrcerer.messaging.pagination;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public interface PaginatedEmbed {
    void removePage(int pageNumber);

    MessageEmbed getPage(int pageNumber);

    void addPage(EmbedBuilder rawPage);
}