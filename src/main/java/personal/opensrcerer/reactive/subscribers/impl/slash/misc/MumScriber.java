package personal.opensrcerer.reactive.subscribers.impl.slash.misc;

import personal.opensrcerer.reactive.payloads.impl.slash.MumEvent;
import personal.opensrcerer.reactive.subscribers.abstractions.SlashCommandSuperscriber;

public class MumScriber extends SlashCommandSuperscriber<MumEvent> {
    @Override
    public void onEvent(MumEvent boxed) {
        boxed.reply(boxed.evaluate().value(), false).queue();
    }
}
