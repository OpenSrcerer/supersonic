package personal.opensrcerer.config;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.internal.utils.tuple.Pair;

import java.util.Map;

public enum SlashCommand {

    /* MISCELLANEOUS */
    MUM("areyoumymum", "Answers the most important question in one's life."),

    /* MUSIC PLAYER */
    JOIN("join", "Join a voice channel."),
    PLAY("play", "Play a song by ID.", Map.of(
            "id",
            Pair.of(OptionType.STRING, "The Subsonic ID of the track to play."))
    ),
    SEARCH("search", "Search for songs in the server.", Map.of(
            "query",
            Pair.of(OptionType.STRING, "The searching query."))
    ),
    SKIP("skip", "Skip the song that is currently playing."),
    PAUSE("pause", "Pause the currently playing song."),
    UNPAUSE("unpause", "Unpause the currently playing song."),
    LEAVE("leave", "Leave the voice channel, if currently in one.");

    private final String name;
    private final String description;
    private final Map<String, Pair<OptionType, String>> commandParameters;

    SlashCommand(String name, String description) {
        this.name = name;
        this.description = description;
        this.commandParameters = null;
    }

    SlashCommand(String name, String description, Map<String, Pair<OptionType, String>> params) {
        this.name = name;
        this.description = description;
        this.commandParameters = params;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Pair<OptionType, String>> getParameters() {
        return commandParameters;
    }
}
