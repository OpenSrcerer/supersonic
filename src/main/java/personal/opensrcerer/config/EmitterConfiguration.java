package personal.opensrcerer.config;

import personal.opensrcerer.reactive.emitters.Emitter;
import personal.opensrcerer.reactive.emitters.components.ButtonClickEmitter;
import personal.opensrcerer.reactive.emitters.slash.misc.MumEmitter;
import personal.opensrcerer.reactive.emitters.slash.player.*;
import personal.opensrcerer.reactive.emitters.slash.system.PingEmitter;

import java.util.Set;

public class EmitterConfiguration {

    private static Set<Emitter> emitters = null;

    public static Set<Emitter> get() {
        if (emitters == null) {
            emitters = Set.of(
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
            );
        }
        return emitters;
    }
}
