package personal.opensrcerer.duplex.payloads;

import personal.opensrcerer.duplex.payloads.interfaces.SupersonicEvent;

public enum EventMappingStrategy {
    SLASHCOMMANDEVENT_TO_PLAYEVENT(MappingStrategy.slashCommandEventToPlayEventStrategy);

    private final MappingStrategy<?, ?> mappingStrategy;

    <E, R extends SupersonicEvent> EventMappingStrategy(MappingStrategy<E, R> strategy) {
        this.mappingStrategy = strategy;
    }

    public MappingStrategy<?, ?> get() {
        return mappingStrategy;
    }
}
