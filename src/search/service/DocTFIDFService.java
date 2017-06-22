package search.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import search.dao.DocObjectMapper;
import search.dao.TFIDFObjectMapper;
import search.pojo.DocObject;
import search.pojo.TFIDFObject;

@Service
public class DocTFIDFService {
	@Autowired
	private DocObjectMapper docObjectMapper;
	@Autowired
	private TFIDFObjectMapper tfidfObjectMapper;

	// 后期使用ehcache进行缓存
	public Map<DocObject, List<String>> getAllDocs() {
		return null;
	}

	// 后期使用ehcache进行缓存
	public List<TFIDFObject> getAllTFIDF() {
		return tfidfObjectMapper.selectAllTFIDF();
	}

	public DocObject getDocById(int id) {
		return docObjectMapper.selectByPrimaryKey(id);
	}
}
