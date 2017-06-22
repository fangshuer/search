package search.core;

/**
 * 可以计算出两个字符串之间的相似度，暂时未使用
 * 
 * @author
 *
 * 
 */
public class StringSimialrity {
	public static int fun(String source, String target) {
		int i, j;
		int[][] d = new int[source.length() + 1][target.length() + 1];
		for (i = 1; i < source.length() + 1; i++) {/* 初始化临界值 */
			d[i][0] = i;
		}
		for (j = 1; j < target.length() + 1; j++) {/* 初始化临界值 */
			d[0][j] = j;
		}
		for (i = 1; i < source.length() + 1; i++) {/* 动态规划填表 */
			for (j = 1; j < target.length() + 1; j++) {
				if (source.substring(i - 1, i).equals(target.substring(j - 1, j))) {
					d[i][j] = d[i - 1][j - 1];/* source的第i个和target的第j个相同时 */
				} else {/* 不同的时候则取三种操作最小的一个 */
					d[i][j] = min(d[i][j - 1] + 1, d[i - 1][j] + 1, d[i - 1][j - 1] + 1);
				}
			}
		}
		return d[source.length()][target.length()];
	}

	private static int min(int i, int j, int k) {
		int min = i < j ? i : j;
		min = min < k ? min : k;
		return min;
	}
}
