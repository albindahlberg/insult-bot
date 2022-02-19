package bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.IOException;
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
        JDABuilder setup = JDABuilder.createDefault("OTQzMjQ4MjQyNzgxNjAxODEy.YgwSYQ.8PNyhTlXVoZwRBYStojEgMtglMY").
                addEventListeners(new InsultBot())
                .enableIntents(GatewayIntent.GUILD_MESSAGES,
                                GatewayIntent.GUILD_MESSAGE_TYPING,
                                GatewayIntent.DIRECT_MESSAGE_TYPING);
        JDA insultBot = setup.build();
    }

    /**
     * Generate an insult
     * @return an insult
     * @throws IOException upon web scraping error
     */
    private String createInsult() throws IOException {
        InsultGenerator gen = new InsultGenerator();
        return gen.generateInsult();
    }

    /**
     * Insults targets
     * @param event the event that triggered a response from InsultBot
     * @param targets the members to be insulted
     */
    private void insultTargets(MessageReceivedEvent event, List<Member> targets) {
        for (Member member : targets) {
            String insult;
            try {
                insult = createInsult();
            } catch (IOException e) {
                System.out.println(e);
                return;
            }
            String response = member.getAsMention() + insult;
            event.getChannel().sendMessage(response).queue();
        }
    }

    /**
     * Parses prefix and generates targets for insults
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
                insultTargets(event, members);
            }
        }
    }
}

