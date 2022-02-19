package bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.List;

/**
 * The Bot class build with JDA, see documentation at
 * https://github.com/DV8FromTheWorld/JDA
 */
public class InsultBot extends ListenerAdapter {
    /**
     * Logging in to the Insult-bot
     * @param args input to class call. args[0] is intended to
     *             be the bot token. For simplicity's sake, my
     *             bot token is directly used when logging in.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        JDABuilder setup = JDABuilder.createDefault("OTQzMjQ4MjQyNzgxNjAxODEy.YgwSYQ.yUYPhXxSE-aJq4HrgzaWN9CjzjU").
                addEventListeners(new InsultBot())
                .enableIntents(GatewayIntent.GUILD_MESSAGES,
                                GatewayIntent.GUILD_MESSAGE_TYPING,
                                GatewayIntent.DIRECT_MESSAGE_TYPING);
        JDA insultBot = setup.build();
    }

    /**
     * Generate an insult
     * @return an insult
     */
    private String generateInsult(){
        return " You smell.";
    }

    /**
     * Upon receiving message in the channel, bot will insult
     * the member mentioned in the message
     * @param event an event in the channel
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        // Do not respond to bot messages
        if (event.getAuthor().isBot()) return;

        Message msg = event.getMessage();
        String input = msg.getContentRaw();
        if (input.startsWith(":insult ")) {
            List<Member> members = msg.getMentionedMembers();
            if (!members.isEmpty()) {
                for (Member member : members) {
                    String insult = generateInsult();
                    String response = member.getAsMention() + insult;
                    event.getChannel().sendMessage(response).queue();
                }
            }
        }
    }
}

