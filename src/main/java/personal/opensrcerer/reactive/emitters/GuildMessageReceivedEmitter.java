package personal.opensrcerer.reactive.emitters;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import personal.opensrcerer.messaging.TextChannelMessageParser;
import personal.opensrcerer.reactive.DiscordEventEmitter;
import personal.opensrcerer.handlers.messaging.MessageHandler;

public class GuildMessageReceivedEmitter extends DiscordEventEmitter<GuildMessageReceivedEvent> {

    private final TextChannelMessageParser parser;
    private final MessageHandler handler;

    public GuildMessageReceivedEmitter() {
        super(GuildMessageReceivedEvent.class);
        this.handler = new MessageHandler(this::handleValid);
        this.parser = new TextChannelMessageParser();
    }

    @Override
    public void emit() {
        super.flux().map(parser::parse).subscribe(handler::handle);
    }

    public boolean handleValid(final GuildMessageReceivedEvent event) {
        return (event.getMember() != null && !event.getMember().getUser().isBot());
    }
}
