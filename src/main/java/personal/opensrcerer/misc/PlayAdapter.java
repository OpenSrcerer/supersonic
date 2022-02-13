package personal.opensrcerer.misc;

import net.dv8tion.jda.api.audio.SpeakingMode;
import net.dv8tion.jda.api.audio.hooks.ConnectionListener;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

public abstract class PlayAdapter implements ConnectionListener {
    @Override
    public void onPing(long ping) {
    }

    @Override
    public void onUserSpeaking(@NotNull User user, @NotNull EnumSet<SpeakingMode> modes) {
    }

    @Override
    public void onUserSpeaking(@NotNull User user, boolean speaking) {
    }

    @Override
    public void onUserSpeaking(@NotNull User user, boolean speaking, boolean soundshare) {
    }
}
