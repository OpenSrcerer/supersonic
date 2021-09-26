package personal.opensrcerer.reactive.emitters.slash.player;

import net.dv8tion.jda.api.Permission;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.emitters.slash.SlashEmitter;
import personal.opensrcerer.reactive.sinks.slash.player.LeaveVoiceSink;

public class LeaveVoiceEmitter extends SlashEmitter {
    public LeaveVoiceEmitter() {
        super(
                SlashCommand.LEAVE.getName(),
                new LeaveVoiceSink(
                        Permission.MESSAGE_WRITE
                )
        );
    }
}
