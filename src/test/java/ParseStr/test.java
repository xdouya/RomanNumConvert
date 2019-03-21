package ParseStr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.Test;

import service.ParseStr;
import util.MyUtil;

public class test {
	@Test
	public void testParseRomanStr() {
		File file = new File("testParseRomanData.txt");
		ParseStr parseStr = new ParseStr();
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileInputStream = new FileInputStream(file);
			inputStreamReader = new InputStreamReader(fileInputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			String questionStr = "";
			String expectedAnswerStr = "";
			while ((questionStr = bufferedReader.readLine()) != null) {
				String actualAnswerStr = parseStr.parseRomanStr(questionStr.trim());
				if (!actualAnswerStr.equals("right")) {
					expectedAnswerStr = bufferedReader.readLine().trim();
					Assert.assertEquals(expectedAnswerStr, actualAnswerStr);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
				if (inputStreamReader != null) {
					inputStreamReader.close();
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testCheckIsLegalRomanStr() {
		Assert.assertEquals(true, MyUtil.checkIsLegalRomanStr("MMM"));
		Assert.assertEquals(true, MyUtil.checkIsLegalRomanStr("MCMXLIV"));
		Assert.assertEquals(true, MyUtil.checkIsLegalRomanStr("MMMCMXLIV"));
		Assert.assertEquals(false, MyUtil.checkIsLegalRomanStr("MCMVMLIV"));
	}
}
