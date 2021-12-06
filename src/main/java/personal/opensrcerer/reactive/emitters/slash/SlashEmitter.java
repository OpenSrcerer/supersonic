package personal.opensrcerer.reactive.emitters.slash;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.emitters.DiscordEmitter;
import personal.opensrcerer.reactive.sinks.AuthorizableSink;

/**
 * Implementation of DiscordEventEmitter for Discord Slash Commands.
 * @see SlashCommandEvent
 * @see DiscordEmitter
 */
public abstract class SlashEmitter extends DiscordEmitter<SlashCommandEvent> {

    private final String commandName;

    public SlashEmitter(String commandName, AuthorizableSink<SlashCommandEvent> sink) {
        super(SlashCommandEvent.class, sink);
        this.commandName = commandName;
    }

    @Override
    public void emit() {
        super.topFlux()
                .filter(this::filterValid)
                .filter(topSink()::authorize)
                .subscribe(topSink()::receive);
    }

    @Override
    public boolean filterValid(final SlashCommandEvent event) {
        boolean commandNameMatches = event.getName().equals(commandName);
        boolean eventMemberNotNull = event.getMember() != null;
        boolean guildIsNotNull = event.getGuild() != null;

        return commandNameMatches && eventMemberNotNull && guildIsNotNull;
    }

    @Override
    public AuthorizableSink<SlashCommandEvent> topSink() {
        return (AuthorizableSink<SlashCommandEvent>) super.topSink();
    }
}
