package bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;


public class InsultBot {
    public static void main(String[] arguments) throws Exception
    {
        String BOT_TOKEN = "OTQzMjQ4MjQyNzgxNjAxODEy.YgwSYQ.1E_nPl8UVeUMJr2QOt_1fCezWJ8";
        JDA api = JDABuilder.createDefault(BOT_TOKEN).build();
    }
}
