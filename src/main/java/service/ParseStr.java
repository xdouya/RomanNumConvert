package service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.MyUtil;

public class ParseStr {
	private static final String errorInfo = "I have no idea what you are talking about";
	private Map<String, String> word2RomanNumMap = new HashMap<>();
	private Map<String, Float> item2ArabicNumMap = new HashMap<>();
		
	public String parseRomanStr(String romanStr) {
		String [] romanStrArray = romanStr.split(" ");
		String lowerStr = romanStr.toLowerCase();
		if (MyUtil.checkIsLegalRomanStr(romanStrArray[romanStrArray.length - 1])) {
			return parseWord2RomanNum(romanStr);		
		} else if (lowerStr.endsWith("credits")) {
			return parseWord2ArabicNum(romanStr);
		} else if (lowerStr.startsWith("how much")) {
			return parseHowMuchQueries(romanStr);
		} else if (lowerStr.startsWith("how many")) {
			return parseHowManyQueries(romanStr);
		} else {
			return "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?";
		}
	}
	
	public String parseWord2RomanNum(String inputStr) {
		String [] inputStrArray = inputStr.split(" is ");
		if (inputStrArray.length != 2) {
			return errorInfo;
		}
		String word = inputStrArray[0].trim();
		String romanNum = inputStrArray[1].trim();
		if (MyUtil.checkIsLegalRomanStr(romanNum)) {
			word2RomanNumMap.put(word.toLowerCase(), romanNum);
		}else {
			return errorInfo;
		}
		return "right";
	}
	
	public String parseWord2ArabicNum(String inputStr) {
		String [] inputStrArray = inputStr.split(" is ");
		if (inputStrArray.length != 2) {
			return errorInfo;
		}
		String beforeIs = inputStrArray[0].trim();
		String afterIs = inputStrArray[1].trim();
		String[] beforeIsArray = beforeIs.split(" ");
		List<String> beforeIsList = Arrays.asList(beforeIsArray);
		if (beforeIsList.isEmpty()) {
			return errorInfo;
		}
		String unknownWord = beforeIsList.get(beforeIsList.size() - 1).toLowerCase();
		beforeIsList = beforeIsList.subList(0, beforeIsList.size() - 1);
		if (beforeIsList.isEmpty()) {
			return errorInfo;
		}
		String romanNumStr = getRomanNumStr(beforeIsList);
		if (romanNumStr == null) {
			return errorInfo;
		}
		String[] afterIsArray = afterIs.split(" ");
		if (afterIsArray.length != 2) {
			return errorInfo;
		}
		String num = afterIsArray[0];
		if (MyUtil.checkIsNumStr(num)) {
			float value = (float) (Integer.parseInt(num) / (Covert.convert(romanNumStr)*1.0));
			item2ArabicNumMap.put(unknownWord, value);
		} else {
			return errorInfo;
		}
		return "right";
	}
	
	public String parseHowMuchQueries(String inputStr) {
		String [] inputStrArray = inputStr.split(" is ");
		if (inputStrArray.length != 2) {
			return errorInfo;
		}
		String afterIs = inputStrArray[1].trim();
		if (!afterIs.endsWith(" ?")) {
			return errorInfo;
		}
		String[] afterIsArray = afterIs.split(" ");
		List<String> afterIsList = Arrays.asList(afterIsArray);
		afterIsList = afterIsList.subList(0, afterIsList.size() - 1);
		String romanNumStr = getRomanNumStr(afterIsList);
		if (romanNumStr == null) {
			return errorInfo;
		}
		int sum = Covert.convert(romanNumStr);
		return String.join(" ", afterIsList) + " is " + sum;
	}
	
	public String parseHowManyQueries(String inputStr) {
		String [] inputStrArray = inputStr.split(" is ");
		if (inputStrArray.length != 2) {
			return errorInfo;
		}
		String afterIs = inputStrArray[1].trim();
		if (!afterIs.endsWith(" ?")) {
			return errorInfo;
		}
		String[] afterIsArray = afterIs.split(" ");
		List<String> wordList = Arrays.asList(afterIsArray);
		if (wordList.isEmpty()) {
			return errorInfo;
		}
		wordList = wordList.subList(0, wordList.size() - 1);
		String itemWord = wordList.get(wordList.size() - 1);
		List<String> romanWordList = wordList.subList(0, wordList.size() - 1);
		String romanNumStr = getRomanNumStr(romanWordList);
		if (romanNumStr == null) {
			return errorInfo;
		}
		if (item2ArabicNumMap.containsKey(itemWord.toLowerCase())) {
			int value = (int) (Covert.convert(romanNumStr) * item2ArabicNumMap.get(itemWord.toLowerCase()));
			return String.join(" ", romanWordList) + " " + itemWord + " is " + value + " Credits";
		}else {
			return errorInfo;
		}
	}
	
	private String getRomanNumStr(List<String> list) {
		if (list.isEmpty()) {
			return null;
		}
		StringBuffer romanNumBuffer = new StringBuffer();
		if (MyUtil.checkWordIsKnown(list, word2RomanNumMap)) {
			for (String word : list) {
				romanNumBuffer.append(word2RomanNumMap.get(word.toLowerCase()));
			}
		} else {
			return null;
		}
		return romanNumBuffer.toString();
	}
}
