package search.dao;

import java.util.List;

import search.pojo.TFIDFObject;

public interface TFIDFObjectMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(TFIDFObject record);

	int insertSelective(TFIDFObject record);

	TFIDFObject selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(TFIDFObject record);

	int updateByPrimaryKey(TFIDFObject record);

	// 自定义开始
	List<TFIDFObject> selectAllTFIDF();
}