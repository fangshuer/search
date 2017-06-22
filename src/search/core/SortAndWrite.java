package search.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 第三步：将获取到的文章和其tf-idf结果排序，取前{@link STORENUM}个关键字保存
 * 
 * @author
 *
 */
public class SortAndWrite {
	private static final int STORENUM = 20;

	public static List<Entry<String, Float>> sort(Integer id, HashMap<String, Float> tdidf) {
		Set<Entry<String, Float>> entrys = tdidf.entrySet();
		Iterator<Entry<String, Float>> iterator = entrys.iterator();
		List<Entry<String, Float>> unsortedList = new ArrayList<Map.Entry<String, Float>>();
		while (iterator.hasNext()) {
			unsortedList.add(iterator.next());
		}
		Collections.sort(unsortedList, new Comparator<Entry<String, Float>>() {

			public int compare(Entry<String, Float> o1, Entry<String, Float> o2) {
				// TODO Auto-generated method stub

				return -o1.getValue().compareTo(o2.getValue());
			}

		});
		// 存储排序结果并返回
		List<Entry<String, Float>> out = new ArrayList<Entry<String, Float>>();
		int count = 0;
		for (Entry<String, Float> entry : unsortedList) {
			if (count >= STORENUM)
				break;
			count++;
			out.add(entry);

		}
		return out;
	}

	// public static void main(String[] args) throws Exception {
	// String file = "D:/testfiles";
	//
	// HashMap<String, HashMap<String, Float>> all_tf =
	// ReadFiles.tfAllFiles(file);
	// // System.out.println();
	// HashMap<String, Float> idfs = ReadFiles.idf(all_tf);
	// // System.out.println();
	// HashMap<String, HashMap<String, Float>> tf_idf = ReadFiles.tf_idf(all_tf,
	// idfs);
	// Set<Entry<String, HashMap<String, Float>>> entrySet = tf_idf.entrySet();
	// Iterator<Entry<String, HashMap<String, Float>>> iterator =
	// entrySet.iterator();
	// if (outputFile.exists())
	// outputFile.delete();
	// outputFile.createNewFile();
	// FileOutputStream fos = new FileOutputStream(outputFile);
	// StringBuilder sb = new StringBuilder();
	// while (iterator.hasNext()) {
	// sb = new StringBuilder();
	// Entry<String, HashMap<String, Float>> next = iterator.next();
	// List<Entry<String, Float>> sort = sort(next.getKey(), next.getValue());
	// sb.append(next.getKey() + "-----");
	// for (Entry<String, Float> string : sort) {
	// sb.append(" " + string.getKey() + ":" + string.getValue());
	// }
	// fos.write(sb.toString().getBytes());
	// fos.write('\r');
	// fos.write('\n');
	// sb = null;
	// }
	// fos.flush();
	// fos.close();
	// }
}
