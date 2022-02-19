package bot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * An insult web scraper
 */
public class InsultGenerator {

    /**
     * Scrapes the URL for an insult to return
     * @return an insult from the URL
     * @throws IOException upon invalid URL
     */
    public String generateInsult() throws IOException {
        Document doc = Jsoup.connect("https://thoughtcatalog.com/january-nelson/2021/01/best-insults/").get();
        Elements insults = doc.getElementsByClass("li1");

        // Generate randomness
        int randomIndex = (int)(Math.random() * insults.size());

        // Get random insult
        String insult = ' ' + insults.get(randomIndex).text();

        return insult;
    }
}
