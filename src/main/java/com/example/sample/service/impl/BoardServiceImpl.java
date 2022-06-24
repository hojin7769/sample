package com.example.sample.service.impl;

import java.util.List;
import java.util.Map;

import com.example.sample.mapper.BoardMapper;
import com.example.sample.model.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sample.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardVO> list() {
		return boardMapper.list();
	}

	@Override
	public BoardVO detail(BoardVO boardVO) {
		return boardMapper.detail(boardVO);
	}

	@Override
	@Transactional
	public BoardVO insert(BoardVO boardVO) {
		int rtn = boardMapper.insert(boardVO);
		return boardVO;
	}

	@Override
	@Transactional
	public BoardVO update(BoardVO boardVO) {
		int rtn = boardMapper.update(boardVO);
		return boardVO;
	}

	@Override
	@Transactional
	public BoardVO delete(BoardVO boardVO) {
		int rtn = boardMapper.delete(boardVO);
		return boardVO;
	}

}
