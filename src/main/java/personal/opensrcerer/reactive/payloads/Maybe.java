package personal.opensrcerer.reactive.payloads;

import net.dv8tion.jda.api.entities.MessageEmbed;

public record Maybe<T>(T value, MessageEmbed err) {

    public Maybe(T t) {
        this(t, null);
    }

    public Maybe(MessageEmbed embed) {
        this(null, embed);
    }

    public boolean ok() {
        return this.err == null;
    }
}
