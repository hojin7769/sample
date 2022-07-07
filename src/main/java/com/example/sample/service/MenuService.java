package com.example.sample.service;

import com.example.sample.model.MenuVO;

import java.util.List;
import java.util.Map;

public interface MenuService {


    public List<MenuVO> list();
    public MenuVO detail(MenuVO menuVO);
    public int insert(MenuVO menuVO);
    public int update(MenuVO menuVO);
    public int delete(MenuVO menuVO);
    public int updateDel(MenuVO menuVO);
    public Map<String,Object> createTable(Map<String,Object> map);
}
