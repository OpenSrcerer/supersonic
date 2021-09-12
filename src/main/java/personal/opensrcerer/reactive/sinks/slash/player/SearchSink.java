package personal.opensrcerer.reactive.sinks.slash.player;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.apache.commons.lang3.StringUtils;
import personal.opensrcerer.client.SubsonicClient;
import personal.opensrcerer.reactive.sinks.slash.SlashCommandSink;
import personal.opensrcerer.requests.search.Search2;
import personal.opensrcerer.responses.entities.Song;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class SearchSink extends SlashCommandSink {

    public SearchSink(Permission... permissions) {
        super(permissions);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void receive(SlashCommandEvent event) {
        OptionMapping o = event.getOption("query");

        if (o == null) {
            event.reply("Missing query parameter!").queue();
            return;
        }

        var abc = SubsonicClient.INSTANCE.request(
                new Search2(
                        Map.of(
                                "query", o.getAsString(),
                                "songCount", "10"
                        )
                ),
                event.getGuild().getId()
        );

        if (abc.getSongs().length == 0) {
            event.reply("No results!").queue();
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("```css\n");
        builder.append(String.format("%-10s%-25s%-20s%n%n", "ID", "TITLE", "ALBUM"));
        Arrays.stream(abc.getSongs())
                .sorted(Comparator.comparing(Song::getTitle))
                .forEachOrdered(e -> builder.append(
                        String.format(
                                "%-10s%-25s%-20s%n",
                                e.getId(),
                                StringUtils.abbreviate(e.getTitle(), 22),
                                StringUtils.abbreviate(e.getAlbum(), 17)
                        )
                ));
        builder.append("```");
        event.reply(builder.toString()).queue();
    }
}
