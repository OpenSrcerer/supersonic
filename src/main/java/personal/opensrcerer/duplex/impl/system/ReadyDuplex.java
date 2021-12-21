package personal.opensrcerer.duplex.impl.system;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.RestAction;
import personal.opensrcerer.aspect.DuplexInitializer;
import personal.opensrcerer.aspect.PreDuplex;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.duplex.abstractions.DiscordDuplex;
import personal.opensrcerer.launch.SupersonicConstants;

import java.util.EnumSet;

@PreDuplex
public class ReadyDuplex extends DiscordDuplex<ReadyEvent> {
    public ReadyDuplex() {
        super(ReadyEvent.class);
    }

    @Override
    public void onEvent(ReadyEvent readyEvent) {
        initSlashCommands();
        DuplexInitializer.initializePostDuplexes();
    }

    private static void initSlashCommands() {
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

        String test = SupersonicConstants.getVariable("TEST_GUILD_ID");
        if (test != null && !test.isBlank()) {
            Guild guild = SupersonicConstants.getJDA().getGuildById(test);
            if (guild != null) {
                return guild.upsertCommand(data);
            }
        }
        return SupersonicConstants.getJDA().upsertCommand(data);
    }
}
