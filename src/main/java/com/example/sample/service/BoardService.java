package com.example.sample.service;

import com.example.sample.model.BoardVO;

import java.util.List;
import java.util.Map;

public interface BoardService {

	public List<BoardVO> list();
	public BoardVO detail(BoardVO boardVO);
	public BoardVO insert(BoardVO boardVO);
	public BoardVO update(BoardVO boardVO);
	public BoardVO delete(BoardVO boardVO);
}
