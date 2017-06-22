package search;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;

import search.core.Segmentation;

/**
 * 分词质量测试
 * 
 * @author
 * 
 *
 */
public class Test {
	public static void testSeg_str() {

		Segmentation seg = new Segmentation();
		String str[] = { "按自己喜欢选择的，质量不错", "结婚的和尚未结婚的都需要登记", "他说的确实在理", "阿拉斯加遭强暴风雪袭击致xx人死亡", "邓颖超生前使用过的物品 ", "乒乓球拍卖完了",
				"吴江西陵印刷厂","千变万化" };
		for (String string : str) {
			System.out.println(seg.seg_list(string));
		}

		JiebaSegmenter jb = new JiebaSegmenter();
		for (String string : str) {
			System.out.println(jb.process(string, SegMode.INDEX).toString());
		}

	}

	public static void main(String[] args) {
		testSeg_str();
	}
}
