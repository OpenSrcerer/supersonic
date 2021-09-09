package personal.opensrcerer.reactive.sinks.slash;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.sinks.AuthorizableSink;

public abstract class SlashCommandSink extends AuthorizableSink<SlashCommandEvent> {
    public SlashCommandSink(Permission... permissions) {
        super(permissions);
    }
}
