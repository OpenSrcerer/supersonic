package personal.opensrcerer.reactive.emitters.slash.player;

import net.dv8tion.jda.api.Permission;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.emitters.slash.SlashCommandEmitter;
import personal.opensrcerer.reactive.sinks.slash.player.JoinVoiceSink;

public class JoinVoiceEmitter extends SlashCommandEmitter {

    private final JoinVoiceSink sink;

    public JoinVoiceEmitter() {
        super(SlashCommand.JOIN.getName());
        this.sink = new JoinVoiceSink(
                Permission.MESSAGE_WRITE,
                Permission.VIEW_CHANNEL,
                Permission.VOICE_CONNECT,
                Permission.VOICE_SPEAK
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
