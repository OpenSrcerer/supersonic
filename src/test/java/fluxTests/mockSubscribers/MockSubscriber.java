package fluxTests.mockSubscribers;

import fluxTests.MockMappingStrategy;
import fluxTests.mockEvents.BlahEvent;
import fluxTests.mockMulticaster.TestMockEventMulticaster;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.subscribers.abstractions.GenericSuperscriber;
import reactor.core.publisher.Flux;

public abstract class MockSubscriber extends GenericSuperscriber<SlashCommandEvent, BlahEvent> {

    public MockSubscriber() {
        this.setTranslation(SlashCommandEvent.class, MockMappingStrategy.slashCommandEventToBlahEvent);
    }

    @Override
    public void subscribe() {
        Flux<SlashCommandEvent> multicaster = TestMockEventMulticaster.of(super.eventToCapture);
        Flux<BlahEvent> mappedMulticaster;

        mappedMulticaster = multicaster.map(super.mappingStrategy);
        mappedMulticaster
                .doOnNext(this::onEvent)
                .checkpoint()
                .onErrorContinue((t, r) -> onError(t, (BlahEvent) r))
                .subscribe();
    }
}
