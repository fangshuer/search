package search.dao;

import java.util.List;

import search.pojo.DocObject;

public interface DocObjectMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(DocObject record);

	int insertSelective(DocObject record);

	DocObject selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(DocObject record);

	int updateByPrimaryKey(DocObject record);

	List<DocObject> selectAllDoc();
}