package personal.opensrcerer.reactive.emitters.emitters.slashCommands;

import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.sinks.slash.JoinVoiceSink;

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
