package personal.opensrcerer.reactive.emitters.slashCommands;

import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.sinks.slash.MumSink;

public class MumEmitter extends SlashCommandEmitter {

    private final MumSink sink;

    public MumEmitter() {
        super(SlashCommand.MUM.getName());
        this.sink = new MumSink();
    }

    @Override
    public void emit() {
        super.flux()
                .filter(super::filterValid)
                .subscribe(sink::receive);
    }
}
