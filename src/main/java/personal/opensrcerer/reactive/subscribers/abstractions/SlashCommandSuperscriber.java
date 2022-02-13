package personal.opensrcerer.reactive.subscribers.abstractions;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.reactive.EventMulticaster;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicSlashCommandEvent;

import java.util.EnumSet;
import java.util.List;

public abstract class SlashCommandSuperscriber<R extends SupersonicSlashCommandEvent>
        extends GenericSuperscriber<SlashCommandEvent, R>
        implements Authorizable<SlashCommandEvent>
{
    private String commandName;
    private final EnumSet<Permission> requiredPermissions = EnumSet.noneOf(Permission.class);

    @Override
    public void subscribe() {
        EventMulticaster.of(eventToCapture)
                .filter(event -> event.getName().equals(commandName))
                .map(mappingStrategy)
                .log()
                .doOnNext(this::onEvent)
                .checkpoint()
                .onErrorContinue(this::onError)
                .subscribe();
    }

    @Override
    public void onError(Throwable throwable, Object event) {
        SlashCommandEvent castEvent = (SlashCommandEvent) event;
        if (!castEvent.isAcknowledged()) {
            castEvent.reply("Uh oh! Some error occurred." +
                            "\nReport this error with this message: " + throwable.getMessage()).queue();
        }
        super.onError(throwable, event);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public boolean authorize(SlashCommandEvent event) {
        Member self = event.getGuild().getSelfMember();

        if (requiredPermissions.isEmpty() || self.hasPermission(requiredPermissions)) {
            return true;
        }

        StringBuilder builder = new StringBuilder()
                .append("I am missing the required permissions for this command!" +
                        "\nPlease make sure that I have these permissions:\n\n");
        for (Permission p : requiredPermissions) {
            builder.append(p.getName()).append("\n");
        }

        event.reply(builder.toString()).queue();
        return false;
    }

    public final void setRequiredPermissions(Permission[] requiredPermissions) {
        this.requiredPermissions.addAll(List.of(requiredPermissions));
    }

    public void setCommand(SlashCommand command) {
        this.commandName = command.getName();
    }
}
