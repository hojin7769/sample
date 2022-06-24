package com.example.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sample.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {

	private final static String _MAPPER_NAMESPACE = "com.example.sample.mapper.";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Map<String, Object>> list(String mapperId, Map<String, Object> map) {
		return sqlSessionTemplate.selectList(_MAPPER_NAMESPACE + mapperId, map);
	}

	@Override
	public Map<String, Object> detail(String mapperId, Map<String, Object> map) {
		return sqlSessionTemplate.selectOne(_MAPPER_NAMESPACE + mapperId, map);
	}

	@Override
	@Transactional
	public Map<String, Object> insert(String mapperId, Map<String, Object> map) {
		int rtn = sqlSessionTemplate.insert(_MAPPER_NAMESPACE + mapperId, map);
		return map;
	}

	@Override
	@Transactional
	public Map<String, Object> update(String mapperId, Map<String, Object> map) {
		int rtn = sqlSessionTemplate.update(_MAPPER_NAMESPACE + mapperId, map);
		return map;
	}

	@Override
	@Transactional
	public Map<String, Object> delete(String mapperId, Map<String, Object> map) {
		int rtn = sqlSessionTemplate.delete(_MAPPER_NAMESPACE + mapperId, map);
		return map;
	}

}
