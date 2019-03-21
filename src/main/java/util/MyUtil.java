package util;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MyUtil {
	private static final String romanNumRegex = "M{0,3}(D?C{0,3}|CD|CM)(L?X{0,3}|XC|XL)(V?I{0,3}|IX|IV)";
	private static final String numRegex = "[1-9][0-9]*";
	
	public static boolean checkIsLegalRomanStr(String content) {
		return Pattern.matches(romanNumRegex, content);
	}

	public static boolean checkWordIsKnown(List<String> wordList, Map<String, String> knowWordMap) {
		boolean reslut = true;
		for (String word : wordList) {
			if (!knowWordMap.containsKey(word.toLowerCase())) {
				reslut = false;
			}
		}
		return reslut;
	}
	
	public static boolean checkIsNumStr(String str) {
		return Pattern.matches(numRegex, str);
	}
	
}
