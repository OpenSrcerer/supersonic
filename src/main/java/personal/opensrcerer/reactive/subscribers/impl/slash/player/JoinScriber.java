package personal.opensrcerer.reactive.subscribers.impl.slash.player;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import personal.opensrcerer.aspect.annotations.BoundTo;
import personal.opensrcerer.aspect.annotations.Subscriber;
import personal.opensrcerer.aspect.mapping.EventMappingStrategy;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.payloads.impl.slash.JoinEvent;
import personal.opensrcerer.reactive.subscribers.abstractions.SlashCommandSuperscriber;

@Subscriber(typeToHandle = SlashCommandEvent.class,
        strategy = EventMappingStrategy.SLASHCOMMAND_TO_JOIN)
@BoundTo(SlashCommand.JOIN)
public class JoinScriber extends SlashCommandSuperscriber<JoinEvent> {
    @Override
    public void onEvent(JoinEvent boxed) {
        SlashCommandEvent event = boxed.raw();
        GuildVoiceState state = event.getMember().getVoiceState();
        AudioManager manager = event.getGuild().getAudioManager();
        boolean botConnected = manager.isConnected();
        boolean memberConnected = state.inVoiceChannel();

        if (!memberConnected) {
            event.reply("You are not in a voice channel!").queue();
            return;
        }

        VoiceChannel memberChannel = state.getChannel();
        if (botConnected && !manager.getConnectedChannel().getId().equals(memberChannel.getId())) {
            manager.openAudioConnection(memberChannel);
            event.reply("Switched channels to: <#" + memberChannel.getId() + ">").queue();
            return;
        }

        if (botConnected && manager.getConnectedChannel().getId().equals(memberChannel.getId())) {
            event.reply("The bot is already in <#" + memberChannel.getId() + ">").queue();
            return;
        }

        manager.openAudioConnection(memberChannel);
        event.reply("Joined <#" + memberChannel.getId() + ">").queue();
    }
}
