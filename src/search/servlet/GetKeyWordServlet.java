package search.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import search.pojo.DocObject;
import search.pojo.TFIDFObject;
import search.service.DocTFIDFService;

/**
 * Servlet implementation class GetKeyWordServlet
 */
public class GetKeyWordServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private DocTFIDFService docTFIDFService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetKeyWordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String keyWord = request.getParameter("q");
		System.out.println("关键字是:" + keyWord);
		request.setAttribute("q", keyWord);
		if ("".equals(keyWord.trim())) {
			request.setAttribute("docList", null);
		} else {
			// 1,将关键字拆分---分词
			Set<String> keys = new HashSet<String>();// 用于存放匹配到的关键字
			keys.add(keyWord);
			// 2,根据关键字匹配数据库中的文章
			HashMap<Integer, Float> ansHashmap = new HashMap<Integer, Float>(200);
			// 获取所有的关键字集合
			List<TFIDFObject> allTFIDF = docTFIDFService.getAllTFIDF();

			for (TFIDFObject tfidfObject : allTFIDF) {
				if (tfidfObject.getKeyword().contains(keyWord) || keyWord.contains(tfidfObject.getKeyword())) {
					keys.add(tfidfObject.getKeyword().length() > keyWord.length() ? keyWord : tfidfObject.getKeyword());
					if (ansHashmap.containsKey(tfidfObject.getDocId())) {
						float next = tfidfObject.getTfidf() + ansHashmap.get(tfidfObject.getDocId());
						ansHashmap.put(tfidfObject.getDocId(), next);
					} else {
						ansHashmap.put(tfidfObject.getDocId(), tfidfObject.getTfidf());
					}
				}
			}
			// 排序取前n个

			List<Entry<Integer, Float>> unsortedList = new ArrayList<Entry<Integer, Float>>(ansHashmap.entrySet());
			Collections.sort(unsortedList, new Comparator<Entry<Integer, Float>>() {
				public int compare(Entry<Integer, Float> obj1, Entry<Integer, Float> obj2) {
					// return (o2.getValue() - o1.getValue());
					return -(obj1.getValue()).compareTo(obj2.getValue());
				}
			});
			// 排好序后，针对关键字和文章题目做对比，

			// 取出前10条，返回给客户端
			int count = 0;
			List<DocObject> out = new ArrayList<DocObject>();
			for (Entry<Integer, Float> entry : unsortedList) {
				if (count >= 10)
					break;
				count++;
				DocObject docObj = docTFIDFService.getDocById(entry.getKey());
				if (docObj.getName().equals(keyWord)) {
					out.add(0, docObj);
				} else {
					out.add(docObj);
				}
				System.out.println(entry.getKey() + ":" + entry.getValue());

			}
			if (out.size() == 0) {
				request.setAttribute("docList", null);
			} else {
				request.setAttribute("keys", new ArrayList<String>(keys));
				request.setAttribute("docList", out);
			}
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		// response.sendRedirect("/search/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
