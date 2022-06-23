package com.example.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sample.dao.BoardDAO;
import com.example.sample.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;

	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		return boardDAO.list(map);
	}

	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		return boardDAO.detail(map);
	}

	@Override
	@Transactional
	public Map<String, Object> insert(Map<String, Object> map) {
		int rtn = boardDAO.insert(map);
		return map;
	}

	@Override
	@Transactional
	public Map<String, Object> update(Map<String, Object> map) {
		int rtn = boardDAO.update(map);
//		boardDAO.exeception(map);
		return map;
	}
	

	@Override
	@Transactional
	public Map<String, Object> delete(Map<String, Object> map) {
		int rtn = boardDAO.delete(map);
		return map;
	}

}
