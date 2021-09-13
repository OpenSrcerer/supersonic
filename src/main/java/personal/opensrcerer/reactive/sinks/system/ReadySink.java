package personal.opensrcerer.reactive.sinks.system;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.RestAction;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.launch.SupersonicConstants;
import personal.opensrcerer.reactive.sinks.Sink;

import java.util.EnumSet;

public class ReadySink implements Sink<ReadyEvent> {
    @Override
    public void receive(ReadyEvent readyEvent) {
        EnumSet<SlashCommand> commands = EnumSet.allOf(SlashCommand.class);

        if (commands.isEmpty()) {
            return;
        }

        RestAction<?> action = null;

        for (SlashCommand command : commands) {
            if (action == null) {
                action = getAction(command);
            }
            action = action.and(getAction(command));
        }

        action.queue();
    }

    private static RestAction<Command> getAction(SlashCommand command) {
        CommandData data = new CommandData(
                command.getName(),
                command.getDescription()
        );

        if (command.getParameters() != null) {
            command.getParameters().forEach((name, td) -> data.addOption(td.getLeft(), name, td.getRight(), true));
        }

        return SupersonicConstants.getJDA().upsertCommand(data);
    }
}
