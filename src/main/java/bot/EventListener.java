package bot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventListener extends ListenerAdapter {


    public void onMessageRecieved(MessageReceivedEvent event) {
        // Don't respond to other bots
        if (event.getAuthor().isBot()) return;

    }
}
