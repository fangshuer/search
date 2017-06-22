package search.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ResourceUtils;

public class DictionaryUtil {
	static Map<String, Integer> dic = new HashMap<String, Integer>(160000);
	static Map<String, Integer> stopWords = new HashMap<String, Integer>(1000);
	static String projectDir = "classpath:dictionary";
	static {
		// loadDic("dictionary/SougouLabDic_utf8.txt");

		loadDic(projectDir + "/dic_utf8.txt");
	}

	public static void loadDic(String name) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(
					new InputStreamReader(new FileInputStream(ResourceUtils.getFile("classpath:dictionary/dic.txt"))));
			// br = new BufferedReader(new InputStreamReader(new
			// FileInputStream(
			// DictionaryUtil.class.getClassLoader()
			// .getResource("dictionary/SogouLabDic_utf8.dic")
			// .getFile()), "utf-8"));

			String line;
			int end;
			while ((line = br.readLine()) != null) {
				end = line.indexOf('\t');
				if (end != -1)
					dic.put(line.substring(0, end), 1);
				else {
					dic.put(line, 1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("dic load success,have been load " + dic.size() + " words!");
	}

	public static boolean match(String word) {
		System.out.println("dic的长度" + dic.size());
		if (dic.containsKey(word))
			return true;
		return false;
	}

	public static boolean matchStopWords(String word) {
		if (stopWords.containsKey(word))
			return true;
		return false;
	}
}
