package com.ba;


import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;
import java.util.List;

public class Cnn extends BaseModel {

    public List<SyndEntry> getEntries() {

        try {
            String url = "https://www.ntv.com.tr/son-dakika.rss";


            XmlReader reader = new XmlReader(new URL(url));

            SyndFeed feed = new SyndFeedInput().build(reader);

            System.out.println(feed.getTitle());
            System.out.println("***********************************");

            return feed.getEntries();

/*
                for (SyndEntry entry : feed.getEntries()) {
                    System.out.println(entry);
                    System.out.println("***********************************");
                }
*/

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
