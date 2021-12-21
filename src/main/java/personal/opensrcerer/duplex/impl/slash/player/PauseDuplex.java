package personal.opensrcerer.duplex.impl.slash.player;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.duplex.abstractions.SlashCommandDuplex;
import personal.opensrcerer.services.audio.MusicPlayer;

public class PauseDuplex extends SlashCommandDuplex {
    public PauseDuplex() {
        super(SlashCommand.PAUSE.getName());
    }

    @Override
    public void onEvent(SlashCommandEvent event) {
        MusicPlayer.MUSIC_PLAYER.pause(event.getGuild());
        event.reply("Player paused!").queue();
    }
}
