package personal.opensrcerer.reactive.emitters.slash.player;

import net.dv8tion.jda.api.Permission;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.emitters.slash.SlashEmitter;
import personal.opensrcerer.reactive.sinks.slash.player.JoinVoiceSink;

public class JoinVoiceEmitter extends SlashEmitter {

    public JoinVoiceEmitter() {
        super(
                SlashCommand.JOIN.getName(),
                new JoinVoiceSink(
                        Permission.MESSAGE_WRITE,
                        Permission.VIEW_CHANNEL,
                        Permission.VOICE_CONNECT,
                        Permission.VOICE_SPEAK
                )
        );
    }
}
