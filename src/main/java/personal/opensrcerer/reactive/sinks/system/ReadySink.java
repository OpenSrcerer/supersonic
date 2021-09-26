package personal.opensrcerer.reactive.sinks.system;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.RestAction;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.launch.SupersonicConstants;
import personal.opensrcerer.reactive.emitters.Emitter;
import personal.opensrcerer.reactive.emitters.components.ButtonClickEmitter;
import personal.opensrcerer.reactive.emitters.slash.misc.MumEmitter;
import personal.opensrcerer.reactive.emitters.slash.player.*;
import personal.opensrcerer.reactive.emitters.slash.system.PingEmitter;
import personal.opensrcerer.reactive.emitters.system.ReadyEmitter;
import personal.opensrcerer.reactive.sinks.Sink;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class ReadySink implements Sink<ReadyEvent> {
    @Override
    public void onEvent(ReadyEvent readyEvent) {
        initSlashCommands();
        initEmitters();
    }

    private static void initEmitters() {
        Set.of(
                new ButtonClickEmitter(),
                new MumEmitter(),
                new JoinVoiceEmitter(),
                new LeaveVoiceEmitter(),
                new PauseEmitter(),
                new PlayEmitter(),
                new SearchEmitter(),
                new SkipEmitter(),
                new UnpauseEmitter(),
                new PingEmitter()
        ).forEach(Emitter::emit);
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
