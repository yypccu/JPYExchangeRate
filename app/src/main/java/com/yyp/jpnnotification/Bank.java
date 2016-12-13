package com.yyp.jpnnotification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by I5-4430 on 2016/12/7.
 */

public final class Bank {

    public static final String getDate() {
        String raw = "";
        try {
            // Make a URL to the web page
            URL url = new URL("http://rate.bot.com.tw/xrt/flcsv/0/day");

            // Get the input stream through URL Connection
            URLConnection con = url.openConnection();

            raw = con.getHeaderField("Content-Disposition");

        } catch (IOException e) {
            System.out.print(e);
        }

        Pattern pattern = Pattern.compile("\\d{12}");
        Matcher matcher = pattern.matcher(raw);

        if(matcher.find()) {
            return DateNormalize(matcher.group());
        } else {
            return null;
        }
    }

    public static final String getJpyPrice() {
        String in = "";
        try {
            // Make a URL to the web page
            URL url = new URL("http://rate.bot.com.tw/xrt/flcsv/0/day");

            // Get the input stream through URL Connection
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            // read each line and write to System.out
            while ((line = br.readLine()) != null) {
                if(line.contains("JPY")) {
                    in = line;
                }
            }
        } catch (IOException e) {
            System.out.print(e);
        }

        String [] csvObj = in.split(",");
        return csvObj[12];
    }

    private static final String DateNormalize(String date) {
        SimpleDateFormat orgineDataFormat = new SimpleDateFormat("yyyyMMddHHmm");
        SimpleDateFormat desireDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String dateNew = "";
        try {
            dateNew = desireDateFormat.format(orgineDataFormat.parse(date));
        } catch (ParseException e) {
            System.out.print(e);
        }
        return dateNew;
    }
}
