package search.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 第一步:文章分词
 * 
 * @author
 *
 */
public class Segmentation {
	private StringBuilder sb = new StringBuilder();
	private List<String> ls = new ArrayList<String>();
	private int maxwl;// 设置词的最大长度

	/*
	 * @param source 待分词的句子
	 * 
	 * @return 分词后的结果，用空格隔开。
	 */
	public String seg_str(String source) {
		sb.delete(0, sb.length());
		int len;
		String word;
		maxwl = 5;
		while ((len = source.length()) > 0) {
			if (maxwl > len) {
				maxwl = len;
			}
			word = source.substring(len - maxwl);
			int start = len - maxwl;
			boolean find = false;
			// 从左边不断减字，直到匹配成功或者字的长度为一
			while (word.length() > 1) {
				if (DictionaryUtil.match(word)) {
					sb.insert(0, "\t" + word);
					find = true;
					break;
				}
				++start;
				word = source.substring(start);
			}
			if (!find) {
				sb.insert(0, "\t" + word);
			}
			source = source.substring(0, start);
		}
		return sb.toString();
	}

	/*
	 * @param source 待分词的句子
	 * 
	 * @return 分词后的结果，用list存储。
	 */
	public List<String> seg_list(String source) {
		ls.clear();
		int len;
		String word;
		maxwl = 5;
		while ((len = source.length()) > 0) {
			if (maxwl > len) {
				maxwl = len;
			}
			word = source.substring(len - maxwl);
			int start = len - maxwl;
			boolean find = false;
			while (word.length() > 1) {
				if (DictionaryUtil.match(word)) {
					ls.add(word);
					find = true;
					break;
				}
				++start;
				word = source.substring(start);
			}

			// if (!find && word.trim() != " " &&
			// !DictionaryUtil.matchStopWords(word.trim())) {
			// ls.add(word);
			// }

			source = source.substring(0, start);
		}
		Collections.reverse(ls);
		return ls;
	}

}
