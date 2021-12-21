package personal.opensrcerer.duplex.abstractions;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public abstract class SlashCommandDuplex extends AuthDiscordDuplex<SlashCommandEvent>
{
    private final String commandName;

    public SlashCommandDuplex(
            String commandName,
            Permission... permissions
    ) {
        super(SlashCommandEvent.class, permissions);
        this.commandName = commandName;
    }

    @Override
    public void emit() {
        super.flux
                .filter(this::isValid)
                .filter(this::authorize)
                .subscribe(this::receive);
    }

    @Override
    public boolean isValid(final SlashCommandEvent event) {
        boolean commandNameMatches = event.getName().equals(commandName);
        boolean eventMemberNotNull = event.getMember() != null;
        boolean guildIsNotNull = event.getGuild() != null;

        return commandNameMatches && eventMemberNotNull && guildIsNotNull;
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public boolean authorize(SlashCommandEvent event) {
        Member self = event.getGuild().getSelfMember();

        if (self.hasPermission(super.requiredPermissions())) {
            return true;
        }

        StringBuilder builder = new StringBuilder()
                .append("I am missing the required permissions for this command!" +
                        "\nPlease make sure that I have these permissions:\n\n");
        for (Permission p : super.requiredPermissions()) {
            builder.append(p.getName()).append("\n");
        }

        event.reply(builder.toString()).queue();
        return false;
    }

    @Override
    public void onError(SlashCommandEvent event, Throwable t) {
        event.reply("Uh oh! Some error occurred." +
                        "\nReport this error with this message: " + t.getMessage())
                .queue();
        super.onError(event, t);
    }
}
