package personal.opensrcerer.messaging;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import personal.opensrcerer.launch.SupersonicConstants;
import personal.opensrcerer.messaging.dto.ParsedGuildMessageEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextChannelMessageParser {

    public ParsedGuildMessageEvent parse(GuildMessageReceivedEvent event) {
        List<String> arguments = new ArrayList<>(Arrays.asList(event.getMessage().getContentRaw().split("\\s+")));
        return new ParsedGuildMessageEvent(
                event.getChannel(),
                event.getMessage(),
                checkIfBotMentioned(arguments),
                arguments
        );
    }

    private boolean checkIfBotMentioned(List<String> arguments) {
        return arguments.size() >= 1 && arguments.get(0).equalsIgnoreCase("<@!" + SupersonicConstants.BOT_ID + ">");
    }
}
