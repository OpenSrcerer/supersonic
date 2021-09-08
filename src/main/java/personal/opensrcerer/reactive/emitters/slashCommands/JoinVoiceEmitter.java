package personal.opensrcerer.reactive.emitters.slashCommands;

import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.sinks.slash.JoinVoiceSink;

public class JoinVoiceEmitter extends SlashCommandEmitter {

    private final JoinVoiceSink sink;

    public JoinVoiceEmitter() {
        super(SlashCommand.JOIN.getName());
        this.sink = new JoinVoiceSink();
    }

    @Override
    public void emit() {
        super.flux()
                .filter(super::filterValid)
                .subscribe(sink::receive);
    }
}
