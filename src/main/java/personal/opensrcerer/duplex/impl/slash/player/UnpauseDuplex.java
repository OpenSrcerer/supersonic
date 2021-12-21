package personal.opensrcerer.duplex.impl.slash.player;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.aspect.PostDuplex;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.duplex.abstractions.SlashCommandDuplex;
import personal.opensrcerer.services.audio.MusicPlayer;

@PostDuplex
public class UnpauseDuplex extends SlashCommandDuplex {
    public UnpauseDuplex() {
        super(SlashCommand.UNPAUSE.getName());
    }

    @Override
    public void onEvent(SlashCommandEvent event) {
        MusicPlayer.MUSIC_PLAYER.unpause(event.getGuild());
        event.reply("Player unpaused!").queue();
    }
}
