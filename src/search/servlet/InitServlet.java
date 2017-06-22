package search.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import search.core.CalculateTFIDF;
import search.core.SortAndWrite;
import search.dao.DocObjectMapper;
import search.dao.TFIDFObjectMapper;
import search.pojo.DocObject;
import search.pojo.TFIDFObject;

public class InitServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private DocObjectMapper docObjectMapper;
	@Autowired
	private TFIDFObjectMapper tfidfObjectMapper;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long start = System.currentTimeMillis();
		// TODO Auto-generated method stub
		// 取出所有的文章
		ArrayList<DocObject> docs = (ArrayList<DocObject>) docObjectMapper.selectAllDoc();
		// 计算出f-idf值
		HashMap<Integer, HashMap<String, Float>> tf_idf = CalculateTFIDF.calculateTFIDF(docs);

		Set<Entry<Integer, HashMap<String, Float>>> entrySet = tf_idf.entrySet();
		Iterator<Entry<Integer, HashMap<String, Float>>> iterator = entrySet.iterator();
		// 用于存放keyWords的连接。使用,连接
		StringBuilder sb = null;
		DocObject doc = new DocObject();
		TFIDFObject record = null;
		while (iterator.hasNext()) {
			sb = new StringBuilder();
			Entry<Integer, HashMap<String, Float>> next = iterator.next();
			List<Entry<String, Float>> sort = SortAndWrite.sort(next.getKey(), next.getValue());
			// 首先拼接keywords，然后存如doc表中
			for (Entry<String, Float> string : sort) {
				sb.append(string.getKey() + ",");
				// 对于每个key-value将其存入ifidf表中
			}
			doc.setId(next.getKey());
			doc.setKeywords(sb.toString());
			// 更新文章的关键字
			docObjectMapper.updateByPrimaryKeySelective(doc);
			for (Entry<String, Float> string : sort) {
				sb.append(string.getKey() + ",");
				// 对于每个key-value将其存入ifidf表中
				record = new TFIDFObject();
				record.setDocId(next.getKey());
				record.setKeyword(string.getKey());
				record.setTfidf(string.getValue());
				// 对每个关键字，插入到记录tfidf的表中
				tfidfObjectMapper.insertSelective(record);
			}
			sb = null;
		}
		long end = System.currentTimeMillis();
		request.setAttribute("message", "初始化成功，耗时：" + (end - start) / 1000 + "s");
		request.getRequestDispatcher("/initPage.jsp").forward(request, response);

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
