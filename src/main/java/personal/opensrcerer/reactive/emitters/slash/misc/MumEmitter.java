package personal.opensrcerer.reactive.emitters.slash.misc;

import net.dv8tion.jda.api.Permission;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.emitters.slash.SlashCommandEmitter;
import personal.opensrcerer.reactive.sinks.slash.misc.MumSink;

public class MumEmitter extends SlashCommandEmitter {

    private final MumSink sink;

    public MumEmitter() {
        super(SlashCommand.MUM.getName());
        this.sink = new MumSink(
                Permission.VIEW_CHANNEL,
                Permission.MESSAGE_WRITE,
                Permission.ADMINISTRATOR
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
