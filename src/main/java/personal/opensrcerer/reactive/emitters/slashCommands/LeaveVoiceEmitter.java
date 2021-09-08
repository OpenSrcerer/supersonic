package personal.opensrcerer.reactive.emitters.slashCommands;

import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.sinks.slash.LeaveVoiceSink;

public class LeaveVoiceEmitter extends SlashCommandEmitter {

    private final LeaveVoiceSink sink;

    public LeaveVoiceEmitter() {
        super(SlashCommand.LEAVE.getName());
        this.sink = new LeaveVoiceSink();
    }

    @Override
    public void emit() {
        super.flux()
                .filter(super::filterValid)
                .subscribe(sink::receive);
    }
}
