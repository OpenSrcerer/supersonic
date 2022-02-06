package personal.opensrcerer.duplex.payloads.interfaces;

public enum EventMappingStrategy {
    SLASHCOMMANDEVENT_TO_PLAYEVENT(MappingStrategy.slashCommandEventToPlayEventStrategy);

    private final MappingStrategy<?, ?> mappingStrategy;

    <E, R extends SupersonicEvent<E>> EventMappingStrategy(MappingStrategy<E, R> strategy) {
        this.mappingStrategy = strategy;
    }

    public MappingStrategy<?, ?> get() {
        return mappingStrategy;
    }
}
