package com.example.sample.service.impl;

import com.example.sample.model.BoardVO;
import com.example.sample.repository.BoardRepository;
import com.example.sample.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Override
	public Iterable<BoardVO> list(Sort sort) {
		return boardRepository.findAll(sort);
	}

	@Override
	public Optional<BoardVO> detail(BoardVO boardVO) {
		return boardRepository.findById(boardVO.getNO_SEQ());
	}

	@Override
	@Transactional
	public BoardVO insert(BoardVO boardVO) {
		return boardRepository.save(boardVO);
	}

	@Override
	@Transactional
	public BoardVO update(BoardVO boardVO) {
		return boardRepository.save(boardVO);
	}

	@Override
	@Transactional
	public BoardVO delete(BoardVO boardVO) {
		boardRepository.delete(boardVO);
		return boardVO;
	}

}
