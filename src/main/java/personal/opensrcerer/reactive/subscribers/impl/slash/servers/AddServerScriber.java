package personal.opensrcerer.reactive.subscribers.impl.slash.servers;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.aspect.annotations.BoundTo;
import personal.opensrcerer.aspect.annotations.Subscriber;
import personal.opensrcerer.aspect.mapping.EventMappingStrategy;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.db.hibernate.HibernateClient;
import personal.opensrcerer.reactive.payloads.impl.slash.servers.AddServerEvent;
import personal.opensrcerer.reactive.subscribers.abstractions.SlashCommandSuperscriber;

@Subscriber(typeToHandle = SlashCommandEvent.class,
        strategy = EventMappingStrategy.SLASHCOMMAND_TO_ADDSERVER)
@BoundTo(SlashCommand.ADD_SERVER)
public class AddServerScriber extends SlashCommandSuperscriber<AddServerEvent> {
    @Override
    public void onEvent(AddServerEvent event) {
        // HibernateClient.INSTANCE.
    }
}
