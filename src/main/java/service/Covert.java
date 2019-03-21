package service;

import java.util.HashMap;
import java.util.Map;

import javax.swing.text.AbstractDocument.Content;

import util.MyUtil;

public class Covert {
	private static final Map<String, Integer> roman2Arabic = new HashMap<String, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put("I", 1);
			put("IV", 4);
			put("IX", 9);
			put("V", 5);
			put("X", 10);
			put("XL", 40);
			put("XC", 90);
			put("L", 50);
			put("C", 100);
			put("CD", 400);
			put("CM", 900);
			put("M", 1000);
		}
	};

	public static int convert(String romanStr) {
		boolean isLegalRomanStr = MyUtil.checkIsLegalRomanStr(romanStr);
		if (isLegalRomanStr) {
			int sum = getRomanNumSum(romanStr);
			return sum;
		} else {
			return -1;
		}
	}

	private static int getRomanNumSum(String romanNumStr) {
		int sum = 0;
		if (romanNumStr.isEmpty()) {
			return sum;
		}
		if (romanNumStr.length() == 1) {
			sum += roman2Arabic.get(romanNumStr.substring(0, 1));
		} else {
			if (roman2Arabic.get(romanNumStr.substring(0, 2)) == null) {
				sum += roman2Arabic.get(romanNumStr.substring(0, 1)) + roman2Arabic.get(romanNumStr.substring(1));
			} else {
				if (romanNumStr.length() == 2) {
					sum += roman2Arabic.get(romanNumStr.substring(0, 2));
				} else {
					sum += roman2Arabic.get(romanNumStr.substring(0, 2)) + getRomanNumSum(romanNumStr.substring(2));
				}
			}
		}
		return sum;
	}
}
