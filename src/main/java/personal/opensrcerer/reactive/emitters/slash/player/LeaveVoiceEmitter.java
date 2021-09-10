package personal.opensrcerer.reactive.emitters.slash.player;

import net.dv8tion.jda.api.Permission;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.emitters.slash.SlashCommandEmitter;
import personal.opensrcerer.reactive.sinks.slash.player.LeaveVoiceSink;

public class LeaveVoiceEmitter extends SlashCommandEmitter {

    private final LeaveVoiceSink sink;

    public LeaveVoiceEmitter() {
        super(SlashCommand.LEAVE.getName());
        this.sink = new LeaveVoiceSink(
                Permission.MESSAGE_WRITE
        );
    }

    @Override
    public void emit() {
        super.flux()
                .filter(super::filterValid)
                .filter(sink::authorize)
                .subscribe(sink::receive);
    }
}
