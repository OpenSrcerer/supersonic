package personal.opensrcerer.reactive.sinks.slash.player;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.audio.MusicPlayer;
import personal.opensrcerer.reactive.sinks.slash.SlashCommandSink;

public class UnpauseSink extends SlashCommandSink {

    public UnpauseSink(Permission... permissions) {
        super(permissions);
    }

    @Override
    public void receive(SlashCommandEvent event) {
        MusicPlayer.MUSIC_PLAYER.unpause(event.getGuild());
        event.reply("Player unpaused!").queue();
    }
}
