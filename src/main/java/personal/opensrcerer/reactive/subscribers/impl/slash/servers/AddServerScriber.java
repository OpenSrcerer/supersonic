package personal.opensrcerer.reactive.subscribers.impl.slash.servers;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.aspect.annotations.BoundTo;
import personal.opensrcerer.aspect.annotations.Subscriber;
import personal.opensrcerer.aspect.mapping.EventMappingStrategy;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.db.entities.SubsonicServer;
import personal.opensrcerer.db.hibernate.HibernateClient;
import personal.opensrcerer.db.hibernate.ServerActions;
import personal.opensrcerer.reactive.payloads.impl.slash.servers.AddServerEvent;
import personal.opensrcerer.reactive.subscribers.abstractions.SlashCommandSuperscriber;

import java.util.UUID;

@Subscriber(typeToHandle = SlashCommandEvent.class,
        strategy = EventMappingStrategy.SLASHCOMMAND_TO_ADDSERVER)
@BoundTo(SlashCommand.ADD_SERVER)
public class AddServerScriber extends SlashCommandSuperscriber<AddServerEvent> {
    @Override
    public void onEvent(AddServerEvent event) {
        ServerActions.INSTANCE.getGuild("3")
                        .flatMap(result -> ServerActions.INSTANCE.newServer(new SubsonicServer(
                                UUID.randomUUID(),
                                "servertest",
                                "servertest",
                                "servertest",
                                80,
                                "test",
                                "test",
                                "test",
                                result.getResult()
                        )))
                .subscribe(res -> event.reply(res.toString(), false).queue());
    }
}
