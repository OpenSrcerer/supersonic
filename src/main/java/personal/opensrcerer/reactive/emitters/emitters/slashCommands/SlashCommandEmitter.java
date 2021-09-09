package personal.opensrcerer.reactive.emitters.emitters.slashCommands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.emitters.DiscordEventEmitter;

public abstract class SlashCommandEmitter extends DiscordEventEmitter<SlashCommandEvent> {

    private final String commandName;

    public SlashCommandEmitter(String commandName) {
        super(SlashCommandEvent.class);
        this.commandName = commandName;
    }

    @Override
    public boolean filterValid(final SlashCommandEvent event) {
        return event.getName().equals(commandName) && event.getMember() != null;
    }
}
