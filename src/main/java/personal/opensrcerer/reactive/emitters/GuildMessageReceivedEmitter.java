package personal.opensrcerer.reactive.emitters;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import personal.opensrcerer.messaging.TextChannelMessageParser;
import personal.opensrcerer.reactive.DiscordEventEmitter;
import personal.opensrcerer.sinks.messaging.MessageSink;

import javax.annotation.Nonnull;

public class GuildMessageReceivedEmitter extends DiscordEventEmitter<GuildMessageReceivedEvent> {

    private final TextChannelMessageParser parser;
    private final MessageSink sink;

    public GuildMessageReceivedEmitter() {
        super(GuildMessageReceivedEvent.class);
        this.sink = new MessageSink();
        this.parser = new TextChannelMessageParser();
    }

    @Override
    public void emit() {
        super.flux()
                .filter(this::filterValid)
                .map(parser::parse)
                .subscribe(sink::receive);
    }

    @Override
    public boolean filterValid(final GuildMessageReceivedEvent event) {
        return (event.getMember() != null && !event.getMember().getUser().isBot());
    }
}
