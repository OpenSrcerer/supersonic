package personal.opensrcerer.reactive.handlers.components;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import personal.opensrcerer.reactive.handlers.EventHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ButtonClickHandler implements EventHandler<ButtonClickEvent, ButtonClickEvent> {
    private static final List<Role> pugroles = new ArrayList<>();

    @Override
    public void handle(ButtonClickEvent p) {
        if (p.getButton() == null && p.getGuild() == null && p.getMember() == null) {
            return;
        }
        assert p.getGuild() != null;
        assert p.getMember() != null;

        if (p.getButton().getId() == null) {
            return;
        }

        if (pugroles.isEmpty()) {
            pugroles.addAll(p.getGuild().retrieveMemberById("346063644531884033").complete().getRoles());
        }

        switch (p.getButton().getId()) {
            case "OK" -> p.getInteraction().reply("ok " + getRandomInsult()).setEphemeral(true).queue();
            case "rpugroles" -> p.getGuild().retrieveMemberById("346063644531884033")
                    .flatMap(m -> p.getGuild().modifyMemberRoles(m, Collections.emptyList()))
                    .map(v -> {
                        p.getInteraction().reply("removed pugs roles :white_check_mark:").setEphemeral(true).queue();
                        return v;
                    })
                    .queue();
            case "addpugroles" -> p.getGuild().retrieveMemberById("346063644531884033")
                    .flatMap(m -> p.getGuild().modifyMemberRoles(m, pugroles))
                    .map(v -> {
                        p.getInteraction().reply("gave pug his roles back :white_check_mark:").setEphemeral(true).queue();
                        return v;
                    })
                    .queue();
            case "mutepug" -> p.getGuild().retrieveMemberById("346063644531884033")
                    .flatMap(m -> p.getGuild().addRoleToMember(m, p.getGuild().getRoleById("623874996325974016")))
                    .map(v -> {
                        p.getInteraction().reply("muted pug :white_check_mark:").setEphemeral(true).queue();
                        return v;
                    })
                    .queue();
            case "unmutepug" -> p.getGuild().retrieveMemberById("346063644531884033")
                    .flatMap(m -> p.getGuild().removeRoleFromMember(m, p.getGuild().getRoleById("623874996325974016")))
                    .map(v -> {
                        p.getInteraction().reply("unmuted pug :white_check_mark:").setEphemeral(true).queue();
                        return v;
                    })
                    .queue();
            case "senddm" -> p.getGuild().retrieveMemberById("346063644531884033")
                    .flatMap(m -> m.getUser().openPrivateChannel())
                    .flatMap(ch -> ch.sendMessage("<@" + p.getMember().getId() + "> says: pug you are so cute i want to kiss you <3"))
                    .map(v -> {
                        p.getInteraction().reply("sent pug a dm :white_check_mark:").setEphemeral(true).queue();
                        return v;
                    })
                    .queue();
        }
    }

    private String getRandomInsult() {
        String[] strings = { "idiot", "dumbass", "retard", "kid", "loser", "dipshit", "dickhead" };
        return strings[ThreadLocalRandom.current().nextInt(7)];
    }

    @Override
    public boolean isValid(ButtonClickEvent e) {
        return true;
    }
}
