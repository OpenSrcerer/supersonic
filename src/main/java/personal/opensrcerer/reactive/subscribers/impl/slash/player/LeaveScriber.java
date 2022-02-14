package personal.opensrcerer.reactive.subscribers.impl.slash.player;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.aspect.annotations.BoundTo;
import personal.opensrcerer.aspect.annotations.Subscriber;
import personal.opensrcerer.aspect.mapping.EventMappingStrategy;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.payloads.impl.slash.LeaveEvent;
import personal.opensrcerer.reactive.subscribers.abstractions.SlashCommandSuperscriber;
import personal.opensrcerer.services.audio.MusicPlayer;

@Subscriber(typeToHandle = SlashCommandEvent.class,
        strategy = EventMappingStrategy.SLASHCOMMAND_TO_LEAVE)
@BoundTo(SlashCommand.LEAVE)
public class LeaveScriber extends SlashCommandSuperscriber<LeaveEvent> {
    @Override
    public void onEvent(LeaveEvent boxed) {


        MusicPlayer.MUSIC_PLAYER.stop(event.getGuild());
        manager.closeAudioConnection();
        event.reply("Bye bye!").queue();
    }
}
