package io.github.mobomega.somebot;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.*;
import net.dv8tion.jda.api.utils.*;

import javax.security.auth.login.LoginException;
import java.util.logging.Logger;
import java.util.Random;

public class SomeBot extends ListenerAdapter {

    public static String prefix = "%";
    Random random = new Random();

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(args[0]);

        // Enable the bulk delete event
        builder.setBulkDeleteSplittingEnabled(false);
        // Set activity (like "playing Something")
        builder.setActivity(Activity.playing("with emotions"));

        builder.addEventListeners(new SomeBot());

        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);

        builder.setMemberCachePolicy(MemberCachePolicy.ALL);

        builder.build();
        System.out.println("Logged in as SomeBot#2855");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println("Message received:\n" +
                event.getAuthor().getName() + ": " +
                event.getMessage().getContentDisplay()
        );
        System.out.println(event.getAuthor().getId());
        System.out.println(event.getGuild().loadMembers());
        System.out.println(event.getGuild().getMembers());
        System.out.println("---");
        String[] command = event.getMessage().getContentRaw().split(" ");
        if (command[0].startsWith(prefix)) {
            if (command[0].substring(1).equalsIgnoreCase("somebody")) {
                int rand = random.nextInt(event.getGuild().getMembers().size());
                event.getChannel().sendMessage("<@" + event.getGuild().getMembers().get(rand).getId() + ">").queue();
            }
        }

    }
}
