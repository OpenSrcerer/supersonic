package personal.opensrcerer.messaging;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public abstract class EmbedTemplates {
    public static MessageEmbed getEmbed() {
        return new EmbedBuilder()
                .setTitle("Why are you mentioning me?")
                .setDescription("I am a useless proof-of-concept bot (**for now**). \nWell since you did bother me, learn something new:")
                .addField("Version:", "This bot is running on Reactor 3.4.9.", false)
                .addField("What is Reactor?", "" +
                        "Reactor is a fully non-blocking reactive programming foundation for the JVM, with efficient demand management" +
                        "(in the form of managing \"backpressure\"). It offers composable asynchronous sequence APIs -" +
                        "Flux (for [N] elements) and Mono (for [0|1] elements) - and extensively implements the Reactive Streams specification.",
                        false)
                .addField("Learn more:", "https://projectreactor.io/", false)
                .setFooter("Bonkers needs help", "https://cdn.icon-icons.com/icons2/2150/PNG/512/emoji_smiley_sticker_emo_fun_funny_icon_132665.png")
                .build();
    }
}
