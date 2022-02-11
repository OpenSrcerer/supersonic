package personal.opensrcerer.reactive.subscribers.impl.system;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.RestAction;
import personal.opensrcerer.aspect.SubscriberInitializer;
import personal.opensrcerer.aspect.annotations.Phase;
import personal.opensrcerer.aspect.annotations.Subscriber;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.launch.SupersonicConstants;
import personal.opensrcerer.aspect.mapping.EventMappingStrategy;
import personal.opensrcerer.reactive.payloads.impl.system.InitEvent;
import personal.opensrcerer.reactive.subscribers.abstractions.GenericSuperscriber;

import java.util.EnumSet;

@Subscriber(typeToHandle = ReadyEvent.class,
        strategy = EventMappingStrategy.READY_TO_INIT,
        phase = Phase.PRE_READY)
public class Readyscriber extends GenericSuperscriber<ReadyEvent, InitEvent> {
    @Override
    public void onEvent(InitEvent event) {
        initSlashCommands();
        SubscriberInitializer.initializeSubscribers(Phase.POST_READY);
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
