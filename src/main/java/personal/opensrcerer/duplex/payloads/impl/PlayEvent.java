package personal.opensrcerer.duplex.payloads.impl;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import personal.opensrcerer.duplex.payloads.interfaces.SupersonicSlashCommandEvent;
import personal.opensrcerer.duplex.payloads.records.Result;

public class PlayEvent extends SupersonicSlashCommandEvent {

    private final AudioManager manager;
    private final String botChannelId;
    private final String memberChannelId;

    @SuppressWarnings("")
    public PlayEvent(SlashCommandEvent event) {
        super(event);
        manager = event.getGuild().getAudioManager();
        botChannelId = (manager.isConnected()) ? manager.getConnectedChannel().getId() : null;
        memberChannelId = (event.getMember().getVoiceState().inVoiceChannel()) ?
                event.getMember().getVoiceState().getChannel().getId() : null;
    }

    @Override
    public Result evaluate() {
        if (event.getOption("id"). == null) {
            event.reply("Missing ID parameter!").queue();
            return;
        }

        if (botChannelId == null && memberChannelId == null) {
            event.reply("You must join a voice channel to use this command!").queue();
            return;
        }

        if ((memberChannelId == null || !memberChannelId.equals(botChannelId)) && botChannelId != null) {
            event.reply("You must join <#" + botChannelId + "> to be able to add music!").queue();
            return;
        }

        if (botChannelId == null) {
            manager.openAudioConnection(event.getGuild().getVoiceChannelById(memberChannelId));
        }
        return new Result()
    }
}
