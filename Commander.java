package com.crawler;

import java.io.*;
import java.util.*;

/**
 * Created by Jayendrakumar_M on 12/1/2016.
 */
public class Commander {

    private static final int COUNT = 20;
    private Set<String> parsed = new HashSet<String>();
    private List<String> remaining = new LinkedList<String>();

    private String nextUrl() {
        String nextUrl;
        do {
            nextUrl = this.remaining.remove(0);
        } while (this.parsed.contains(nextUrl));
        this.parsed.add(nextUrl);
        return nextUrl;
    }

    public void search(String url)
    {
        while(this.parsed.size() < COUNT)
        {
            String currentUrl;
            Processor p = new Processor();
            if(this.remaining.isEmpty())
            {
                currentUrl = url;
                this.parsed.add(url);
            }
            else
            {
                currentUrl = this.nextUrl();
            }
            p.crawl(currentUrl);

            this.remaining.addAll(p.getLinks());
        }
        System.out.println(String.format("Visited %s web page(s)", this.remaining.size()));
        System.out.println(String.format("processed %s web page(s)", this.parsed.size()));

        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("output.txt"), "utf-8"));
            //writer.write(this.parsed.toString());

            String separator = System.getProperty("line.separator");

            for (String str: this.parsed)
            {
                   writer.write(str + separator);
            }

            System.out.println("File Created Successfully");
        } catch (IOException ex) {
            // report
        } finally {
            try {writer.close();} catch (Exception ex) {/*ignore*/}
        }
    }
}
