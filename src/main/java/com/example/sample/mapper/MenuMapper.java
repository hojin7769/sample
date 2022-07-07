package com.example.sample.mapper;

import com.example.sample.model.MenuVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuMapper {
    public List<MenuVO> list();
    public MenuVO detail(MenuVO menuVO);
    public int insert(MenuVO menuVO);
    public int update(MenuVO menuVO);
    public int delete(MenuVO menuVO);
    public int updateDel(MenuVO menuVO);
    public int maxSeq();
    public Map<String,Object> createTable(Map<String,Object> map);
}
