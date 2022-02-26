package personal.opensrcerer.aspect.mapping;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicEvent;
import personal.opensrcerer.reactive.payloads.impl.components.ClickEvent;
import personal.opensrcerer.reactive.payloads.impl.slash.misc.MumEvent;
import personal.opensrcerer.reactive.payloads.impl.slash.player.*;
import personal.opensrcerer.reactive.payloads.impl.slash.servers.AddServerEvent;
import personal.opensrcerer.reactive.payloads.impl.slash.servers.PingEvent;
import personal.opensrcerer.reactive.payloads.impl.slash.servers.RemoveServerEvent;
import personal.opensrcerer.reactive.payloads.impl.system.InitEvent;

import java.util.function.Function;

public interface MappingStrategy<
        E extends GenericEvent,
        R extends SupersonicEvent<E>
        > extends Function<E, R> {
    MappingStrategy<ButtonClickEvent, ClickEvent> buttonClickToClick = ClickEvent::new;

    MappingStrategy<SlashCommandEvent, AddServerEvent> slashCommandToAddServer = AddServerEvent::new;
    MappingStrategy<SlashCommandEvent, JoinEvent> slashCommandToJoin = JoinEvent::new;
    MappingStrategy<SlashCommandEvent, LeaveEvent> slashCommandToLeave = LeaveEvent::new;
    MappingStrategy<SlashCommandEvent, MumEvent> slashCommandToMum = MumEvent::new;
    MappingStrategy<SlashCommandEvent, PauseEvent> slashCommandToPause = PauseEvent::new;
    MappingStrategy<SlashCommandEvent, PingEvent> slashCommandToPing = PingEvent::new;
    MappingStrategy<SlashCommandEvent, PlayEvent> slashCommandToPlay = PlayEvent::new;
    MappingStrategy<SlashCommandEvent, RemoveServerEvent> slashCommandToRemoveServer = RemoveServerEvent::new;
    MappingStrategy<SlashCommandEvent, SearchEvent> slashCommandToSearch = SearchEvent::new;
    MappingStrategy<SlashCommandEvent, SkipEvent> slashCommandToSkip = SkipEvent::new;
    MappingStrategy<SlashCommandEvent, UnpauseEvent> slashCommandToUnpause = UnpauseEvent::new;

    MappingStrategy<ReadyEvent, InitEvent> readyToInit = InitEvent::new;
}
