package personal.opensrcerer.duplex.impl.slash.player;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.duplex.abstractions.SlashCommandDuplex;
import personal.opensrcerer.services.audio.MusicPlayer;

public class SkipDuplex extends SlashCommandDuplex {
    public SkipDuplex() {
        super(SlashCommand.SKIP.getName());
    }

    @Override
    public void onEvent(SlashCommandEvent event) {
        MusicPlayer.MUSIC_PLAYER.skipTrack(event.getGuild());
        event.reply("Skipped current track!").queue();
    }
}
