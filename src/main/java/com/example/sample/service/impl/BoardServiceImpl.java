package com.example.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sample.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	private final static String _MAPPER_NAMESPACE = "com.example.sample.mapper.";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		return sqlSessionTemplate.selectList(_MAPPER_NAMESPACE + "BoardMapper.list", map);
	}

	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne(_MAPPER_NAMESPACE + "BoardMapper.detail", map);
	}

	@Override
	@Transactional
	public Map<String, Object> insert(Map<String, Object> map) {
		int rtn = sqlSessionTemplate.insert(_MAPPER_NAMESPACE + "BoardMapper.insert", map);
		return map;
	}

	@Override
	@Transactional
	public Map<String, Object> update(Map<String, Object> map) {
		int rtn = sqlSessionTemplate.update(_MAPPER_NAMESPACE + "BoardMapper.update", map);
		return map;
	}


	@Override
	@Transactional
	public Map<String, Object> delete(Map<String, Object> map) {
		int rtn = sqlSessionTemplate.delete(_MAPPER_NAMESPACE + "BoardMapper.delete", map);
		return map;
	}

}
