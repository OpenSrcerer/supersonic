package personal.opensrcerer.reactive.payloads.mock;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.Interaction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EmbedPlayEvent extends SlashCommandEvent {

    private final String selectedSongId;
    private final Interaction interaction;

    private EmbedPlayEvent(
            @NotNull JDA api,
            long responseNumber,
            @NotNull Interaction interaction,
            String selectedSongId
    ) {
        super(api, responseNumber, null);
        this.selectedSongId = selectedSongId;
        this.interaction = interaction;
    }

    public String getSelectedSongId() {
        return selectedSongId;
    }

    public static EmbedPlayEvent from(ButtonClickEvent event, String selectedSongId) {
        return new EmbedPlayEvent(
                event.getJDA(),
                event.getResponseNumber(),
                event.getInteraction(),
                selectedSongId
        );
    }

    @Nullable
    @Override
    public Guild getGuild() {
        return interaction.getGuild();
    }

    @NotNull
    @Override
    public TextChannel getTextChannel() {
        return interaction.getTextChannel();
    }

    @Nullable
    @Override
    public Member getMember() {
        return interaction.getMember();
    }

    @Override
    public boolean isAcknowledged() {
        return interaction.isAcknowledged();
    }

    @NotNull
    @Override
    public ReplyAction reply(@NotNull String content) {
        return interaction.reply(content);
    }

    @NotNull
    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
