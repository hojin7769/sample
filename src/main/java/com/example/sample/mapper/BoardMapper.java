package com.example.sample.mapper;

import com.example.sample.model.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BoardMapper {

    public List<BoardVO> list();
    public BoardVO detail(BoardVO boardVO);
    public int insert(BoardVO boardVO);
    public int update(BoardVO boardVO);
    public int delete(BoardVO boardVO);
}
