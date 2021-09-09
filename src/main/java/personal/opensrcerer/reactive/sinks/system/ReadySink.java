package personal.opensrcerer.reactive.sinks.system;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.RestAction;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.sinks.Sink;

import java.util.EnumSet;

public class ReadySink implements Sink<ReadyEvent> {
    @Override
    public void receive(ReadyEvent readyEvent) {
        EnumSet<SlashCommand> commands = EnumSet.allOf(SlashCommand.class);

        if (commands.isEmpty()) {
            return;
        }

        Guild guild = readyEvent.getJDA()
                .getGuildById("824772718800666645");

        RestAction<?> action = null;

        if (guild != null) {
            for (SlashCommand command : commands) {
                if (action == null) {
                    action = getAction(guild, command);
                }
                action = action.and(getAction(guild, command));
            }

            if (action != null) {
                action.queue();
            }
        }
    }

    private static RestAction<Command> getAction(Guild guild, SlashCommand command) {
        return guild.upsertCommand(
                new CommandData(
                        command.getName(),
                        command.getDescription()
                )
        );
    }
}
