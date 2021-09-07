package personal.opensrcerer.reactive.emitters;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import personal.opensrcerer.messaging.TextChannelMessageParser;
import personal.opensrcerer.reactive.DiscordEventEmitter;
import personal.opensrcerer.handlers.messaging.MessageSink;

public class GuildMessageReceivedEmitter extends DiscordEventEmitter<GuildMessageReceivedEvent> {

    private final TextChannelMessageParser parser;
    private final MessageSink handler;

    public GuildMessageReceivedEmitter() {
        super(GuildMessageReceivedEvent.class);
        this.handler = new MessageSink(this::handleValid);
        this.parser = new TextChannelMessageParser();
    }

    @Override
    public void emit() {
        super.flux().map(parser::parse).subscribe(handler::receive);
    }

    public boolean handleValid(final GuildMessageReceivedEvent event) {
        return (event.getMember() != null && !event.getMember().getUser().isBot());
    }
}
