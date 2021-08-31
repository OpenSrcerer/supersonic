package personal.opensrcerer.messaging.dto;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public record ParsedGuildMessageEvent(
        TextChannel channel,
        Message eventMessage,
        boolean botMentioned,
        List<String> parsed
) {
}
