package personal.opensrcerer.reactive.sinks.slash;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.sinks.AuthorizableSink;

public abstract class SlashCommandSink extends AuthorizableSink<SlashCommandEvent> {
    public SlashCommandSink(Permission[] permissions) {
        super(permissions);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public final boolean authorize(SlashCommandEvent event) {
        Member self = event.getGuild().getSelfMember();

        if (self.hasPermission(super.permissions())) {
            return true;
        }

        StringBuilder builder = new StringBuilder()
                .append("I am missing the required permissions for this command!\nPlease make sure that I have these permissions:\n\n");
        for (Permission p : super.permissions()) {
            builder.append(p.getName()).append("\n");
        }

        event.reply(builder.toString()).queue();
        return false;
    }

    @Override
    public void onError(SlashCommandEvent slashCommandEvent, Throwable t) {
        slashCommandEvent
                .reply("Uh oh! Some error occurred.\nReport this error with this message: " + t.getMessage())
                .queue();
        super.onError(slashCommandEvent, t);
    }
}
