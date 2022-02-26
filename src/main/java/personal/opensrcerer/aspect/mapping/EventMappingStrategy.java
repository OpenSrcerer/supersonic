package personal.opensrcerer.aspect.mapping;

import net.dv8tion.jda.api.events.GenericEvent;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicEvent;

public enum EventMappingStrategy {
    BUTTONCLICK_TO_CLICK(MappingStrategy.buttonClickToClick),

    SLASHCOMMAND_TO_ADDSERVER(MappingStrategy.slashCommandToAddServer),
    SLASHCOMMAND_TO_JOIN(MappingStrategy.slashCommandToJoin),
    SLASHCOMMAND_TO_LEAVE(MappingStrategy.slashCommandToLeave),
    SLASHCOMMAND_TO_MUM(MappingStrategy.slashCommandToMum),
    SLASHCOMMAND_TO_PAUSE(MappingStrategy.slashCommandToPause),
    SLASHCOMMAND_TO_PING(MappingStrategy.slashCommandToPing),
    SLASHCOMMAND_TO_PLAY(MappingStrategy.slashCommandToPlay),
    SLASHCOMMAND_TO_REMOVESERVER(MappingStrategy.slashCommandToRemoveServer),
    SLASHCOMMAND_TO_SEARCH(MappingStrategy.slashCommandToSearch),
    SLASHCOMMAND_TO_SKIP(MappingStrategy.slashCommandToSkip),
    SLASHCOMMAND_TO_UNPAUSE(MappingStrategy.slashCommandToUnpause),

    READY_TO_INIT(MappingStrategy.readyToInit);

    private final MappingStrategy<?, ?> mappingStrategy;

    <E extends GenericEvent, R extends SupersonicEvent<E>> EventMappingStrategy(MappingStrategy<E, R> strategy) {
        this.mappingStrategy = strategy;
    }

    public MappingStrategy<?, ?> get() {
        return mappingStrategy;
    }
}
