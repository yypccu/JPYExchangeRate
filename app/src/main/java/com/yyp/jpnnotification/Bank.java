package com.yyp.jpnnotification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by I5-4430 on 2016/12/7.
 */

public final class Bank {
    private String date;
    private String jpyPrice;
    private String fileName;
    private String JPYToday;

    public Bank() {
        downloadData();
        date = getDate();
        jpyPrice = getJpyPrice();
    }

    private void downloadData() throws IOException{
        WordFinder wordfinder = new WordFinder();
        // Make a URL to the web page
        URL url = new URL("http://rate.bot.com.tw/xrt/flcsv/0/day");

        // Get the input stream through URL Connection
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();

        String raw = con.getHeaderField("Content-Disposition");
        System.out.print(wordfinder.getDate(raw) + '\n');

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        // read each line and write to System.out
        while ((line = br.readLine()) != null) {
            if(line.contains("JPY")) {
                String jpnPrice =  wordfinder.getJPNSellPrice(line);
                System.out.println(jpnPrice);
            }
        }

    }

    public String getDate() {
        return date;
    }

    public String getJpyPrice() {
        return jpyPrice;
    }
}
