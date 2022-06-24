package com.example.sample.service;

import com.example.sample.model.BoardVO;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public interface BoardService {

	public Iterable<BoardVO> list(Sort sort);
	public Optional<BoardVO> detail(BoardVO boardVO);
	public BoardVO insert(BoardVO boardVO);
	public BoardVO update(BoardVO boardVO);
	public BoardVO delete(BoardVO boardVO);
}
