package personal.opensrcerer.config;

public enum SlashCommand {

    MUM("areyoumymum", "Answers the most important question in one's life."),
    JOIN("join", "Join a voice channel."),
    LEAVE("leave", "Leave the voice channel, if currently in one.");

    private final String name;
    private final String description;

    SlashCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
