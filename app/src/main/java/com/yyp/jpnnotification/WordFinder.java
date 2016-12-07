package com.yyp.jpnnotification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordFinder {
	
	public WordFinder() {
		//Nothing
	}
	
	public String findDate(String in) {
		Pattern pattern = Pattern.compile("\\d{12}");
		Matcher matcher = pattern.matcher(in);
		
		if(matcher.find()) {
			return matcher.group();
		} else {
			return null;
		}
	}
	
	public String findJPNSellPrice(String in) {
		String [] csvObj = in.split(",");
		return csvObj[12];
	}
}
