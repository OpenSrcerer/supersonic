package personal.opensrcerer.reactive.emitters.slash;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.emitters.SubsonicDiscordEmitter;
import personal.opensrcerer.reactive.sinks.Sink;
import personal.opensrcerer.requests.SubsonicRequest;

public abstract class SubsonicSlashEmitter<
        R extends SubsonicRequest<R>
        > extends SubsonicDiscordEmitter<
        SlashCommandEvent, R
        > {
    public SubsonicSlashEmitter(Class<SlashCommandEvent> type, Sink<SlashCommandEvent> sink) {
        super(type, sink);
    }

    @Override
    public Sink<SlashCommandEvent> sink() {
        return super.sink();
    }
}
