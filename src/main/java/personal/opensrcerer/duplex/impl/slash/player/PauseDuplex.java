package personal.opensrcerer.duplex.impl.slash.player;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.aspect.annotations.PostDuplex;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.duplex.abstractions.DefaultSlashCommandDuplex;
import personal.opensrcerer.services.audio.MusicPlayer;

@PostDuplex
public class PauseDuplex extends DefaultSlashCommandDuplex {
    public PauseDuplex() {
        super(SlashCommand.PAUSE.getName());
    }

    @Override
    public void onEvent(SlashCommandEvent event) {
        MusicPlayer.MUSIC_PLAYER.pause(event.getGuild());
        event.reply("Player paused!").queue();
    }
}
