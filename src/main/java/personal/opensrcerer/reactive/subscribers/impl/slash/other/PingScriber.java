package personal.opensrcerer.reactive.subscribers.impl.slash.other;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.aspect.annotations.BoundTo;
import personal.opensrcerer.aspect.annotations.Subscriber;
import personal.opensrcerer.aspect.mapping.EventMappingStrategy;
import personal.opensrcerer.client.SubsonicService;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.payloads.impl.slash.PingEvent;
import personal.opensrcerer.reactive.subscribers.abstractions.SlashCommandSuperscriber;
import personal.opensrcerer.requests.system.Ping;

@Subscriber(typeToHandle = SlashCommandEvent.class,
        strategy = EventMappingStrategy.SLASHCOMMAND_TO_PING)
@BoundTo(SlashCommand.PING)
public class PingScriber extends SlashCommandSuperscriber<PingEvent> {
    @Override
    public void onEvent(PingEvent boxed) {
        SlashCommandEvent event = boxed.raw();
        SubsonicService.INSTANCE.request(
                        new Ping(),
                        event.getGuild().getId()
                )
                .subscribe(res -> event.reply(
                        "Pong!\nSubsonic Server Version: **" + res.getVersion() + "**\n"
                                + "Status: **" + res.getStatus() + "**\n"
                                + "Request took: **" + res.getTime() + "ms**\n").queue()
                );
    }
}
