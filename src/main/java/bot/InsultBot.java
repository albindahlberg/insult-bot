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
 * The bot class build with JDA (Java Discord API)
 */
public class InsultBot extends ListenerAdapter {
    public static void main(String[] args) throws Exception
    {
        JDABuilder setup = JDABuilder.createDefault(args[0]).
                addEventListeners(new InsultBot())
                .enableIntents(GatewayIntent.GUILD_MESSAGES,
                                GatewayIntent.GUILD_MESSAGE_TYPING,
                                GatewayIntent.DIRECT_MESSAGE_TYPING);
        JDA insultBot = setup.build();
    }

    private String generateInsult(){
        return "Insult";
    }

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
                    String response = member.toString() + ' ' + insult;
                    event.getChannel().sendMessage(response).queue();
                }
            }
        }
    }
}

