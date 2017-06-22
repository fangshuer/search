package search.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import search.pojo.DocObject;

public class CalculateTFIDF {
	/**
	 * 文章列表(从数据库提取)
	 */
	private static ArrayList<DocObject> docContentList = new ArrayList<DocObject>(); // the
	// list
	// of
	// file

	/**
	 * 分词
	 * 
	 * @param text
	 *            :文章内容
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<String> cutWords(String text) {

		ArrayList<String> words = new ArrayList<String>();
		Segmentation seg = new Segmentation();
		words = (ArrayList<String>) seg.seg_list(text);

		return words;
	}

	/**
	 * 获取每篇文档各个词的词频 TF
	 * 
	 * @param cutwords
	 *            每篇文章的分词结果
	 * @return
	 */
	public static HashMap<String, Integer> normalTF(ArrayList<String> cutwords) {
		HashMap<String, Integer> resTF = new HashMap<String, Integer>();

		for (String word : cutwords) {
			if (resTF.get(word) == null) {
				resTF.put(word, 1);
				// System.out.println(word);
			} else {
				resTF.put(word, resTF.get(word) + 1);
				// System.out.println(word.toString());
			}
		}
		return resTF;
	}

	/**
	 * 
	 * @param cutwords
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static HashMap<String, Float> tf(ArrayList<String> cutwords) {
		HashMap<String, Float> resTF = new HashMap<String, Float>();

		int wordLen = cutwords.size();
		HashMap<String, Integer> intTF = ReadFiles.normalTF(cutwords);

		Iterator iter = intTF.entrySet().iterator(); // iterator for that get
														// from TF
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			resTF.put(entry.getKey().toString(), Float.parseFloat(entry.getValue().toString()) / wordLen);
			// System.out.println(entry.getKey().toString() + " = "
			// + Float.parseFloat(entry.getValue().toString()) / wordLen);
		}
		return resTF;
	}

	// tf times for file
	public static HashMap<String, HashMap<String, Integer>> normalTFAllFiles(String dirc) throws IOException {
		HashMap<String, HashMap<String, Integer>> allNormalTF = new HashMap<String, HashMap<String, Integer>>();

		List<String> docContentList = ReadFiles.readDirs(dirc);
		for (String file : docContentList) {
			HashMap<String, Integer> dict = new HashMap<String, Integer>();
			ArrayList<String> cutwords = ReadFiles.cutWords(file); // get cut
																	// word for
																	// one file

			dict = ReadFiles.normalTF(cutwords);
			allNormalTF.put(file, dict);
		}
		return allNormalTF;
	}

	// tf for all file
	public static HashMap<Integer, HashMap<String, Float>> tfAllFiles() {
		HashMap<Integer, HashMap<String, Float>> allTF = new HashMap<Integer, HashMap<String, Float>>();
		String content = null;
		for (DocObject doc : docContentList) {
			content = doc.getDocAbstract();// 获取摘要
			HashMap<String, Float> dict = new HashMap<String, Float>();
			ArrayList<String> cutwords = cutWords(content);
			dict = ReadFiles.tf(cutwords);
			allTF.put(doc.getId(), dict);
		}
		return allTF;
	}

	/**
	 * 
	 * @param all_tf
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static HashMap<String, Float> idf(HashMap<Integer, HashMap<String, Float>> all_tf) {
		HashMap<String, Float> resIdf = new HashMap<String, Float>();
		HashMap<String, Integer> dict = new HashMap<String, Integer>();
		int docNum = docContentList.size();

		for (int i = 0; i < docNum; i++) {
			HashMap<String, Float> temp = all_tf.get(docContentList.get(i).getId());
			Iterator iter = temp.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String word = entry.getKey().toString();
				if (dict.get(word) == null) {
					dict.put(word, 1);
				} else {
					dict.put(word, dict.get(word) + 1);
				}
			}
		}
		// System.out.println("IDF for every word is:");
		Iterator iter_dict = dict.entrySet().iterator();
		while (iter_dict.hasNext()) {
			Map.Entry entry = (Map.Entry) iter_dict.next();
			float value = (float) Math.log(docNum / Float.parseFloat(entry.getValue().toString()));
			resIdf.put(entry.getKey().toString(), value);
			// System.out.println(entry.getKey().toString() + " = " + value);
		}
		return resIdf;
	}

	@SuppressWarnings("rawtypes")
	public static HashMap<Integer, HashMap<String, Float>> tf_idf(HashMap<Integer, HashMap<String, Float>> all_tf,
			HashMap<String, Float> idfs) {
		HashMap<Integer, HashMap<String, Float>> resTfIdf = new HashMap<Integer, HashMap<String, Float>>();

		int docNum = docContentList.size();
		DocObject doc = null;
		for (int i = 0; i < docNum; i++) {
			doc = docContentList.get(i);
			HashMap<String, Float> tfidf = new HashMap<String, Float>();
			HashMap<String, Float> temp = all_tf.get(doc.getId());
			Iterator iter = temp.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String word = entry.getKey().toString();
				Float value = (float) Float.parseFloat(entry.getValue().toString()) * idfs.get(word);
				tfidf.put(word, value);
			}
			resTfIdf.put(doc.getId(), tfidf);
		}

		return resTfIdf;
	}

	/**
	 * 计算所有文章的tf-dif值，
	 * 
	 * @param list
	 *            所有文章，从数据库查询出来
	 * @return
	 */
	public static HashMap<Integer, HashMap<String, Float>> calculateTFIDF(ArrayList<DocObject> list) {
		docContentList = list;
		HashMap<Integer, HashMap<String, Float>> all_tf = tfAllFiles();
		// System.out.println();
		HashMap<String, Float> idfs = idf(all_tf);
		// System.out.println();
		return tf_idf(all_tf, idfs);
	}

}
