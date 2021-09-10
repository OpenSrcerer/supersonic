package personal.opensrcerer.reactive.emitters.slash;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.emitters.DiscordEventEmitter;
import personal.opensrcerer.reactive.sinks.AuthorizableSink;

/**
 * Implementation of DiscordEventEmitter for Discord Slash Commands.
 * @see SlashCommandEvent
 * @see DiscordEventEmitter
 */
public abstract class SlashCommandEmitter extends DiscordEventEmitter<SlashCommandEvent> {

    private final String commandName;

    public SlashCommandEmitter(String commandName, AuthorizableSink<SlashCommandEvent> sink) {
        super(SlashCommandEvent.class, sink);
        this.commandName = commandName;
    }

    @Override
    public void emit() {
        super.flux()
                .filter(super::filterValid)
                .filter(sink()::authorize)
                .subscribe(sink()::receive);
    }

    @Override
    public boolean filterValid(final SlashCommandEvent event) {
        boolean commandNameMatches = event.getName().equals(commandName);
        boolean eventMemberNotNull = event.getMember() != null;
        boolean guildIsNotNull = event.getGuild() != null;

        return commandNameMatches && eventMemberNotNull && guildIsNotNull;
    }

    @Override
    public AuthorizableSink<SlashCommandEvent> sink() {
        return (AuthorizableSink<SlashCommandEvent>) super.sink();
    }
}
