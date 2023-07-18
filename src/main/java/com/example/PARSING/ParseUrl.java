package com.example.PARSING;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ConcurrentSkipListSet;

public class ParseUrl {
    private Link parent;

    public ParseUrl(Link parent) {
        this.parent = parent;
    }
    public static ConcurrentSkipListSet<Link> parseUrl(Link link){
        ConcurrentSkipListSet<Link> children = new ConcurrentSkipListSet<>();
        Document doc;
        try {
            Thread.sleep(200);
            doc = Jsoup.connect(link.getUrl()).
                    ignoreHttpErrors(true).
                    get();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        Elements links = doc.select("a");
        for (Element elem : links){
            String url = elem.absUrl("href");
            if (url.contains("https://skillbox.ru/") & !isFile(url)){
                Link child = new Link(url);
                children.add(child);
            }
        }
        return children;
    }
    public Link getParent(){
        return parent;
    }
    private static boolean isFile(String link){
        return link.endsWith(".jpg")
                || link.endsWith(".png")
                || link.endsWith(".pdf")
                || link.endsWith(".txt")
                || link.endsWith(".doc")
                || link.contains("#");
    }
}
