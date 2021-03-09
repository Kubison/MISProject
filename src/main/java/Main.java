import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.stream.Collectors;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        Document doc = null;
        ArrayList<String> msg= new ArrayList<String>();
        {
            try {
                doc = Jsoup.connect("https://nyaa.si").get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Elements links = doc.select("a[href][title]");
        for (Element el : links){
            if((el.attr("title").contains("[HorribleSubs]"))&&(el.attr("title").contains("[1080p]"))){
                System.out.println((el.attr("title")));
                msg.add((el.attr("title")));
            }
            }

        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        String apiToken = "";

        String chatId = "";
        String text = msg.stream().map(String::valueOf).collect(Collectors.joining());

        urlString = String.format(urlString, apiToken, chatId, text);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();}
}
}
