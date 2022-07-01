package com.example.sample.service;

import com.example.sample.model.MenuVO;

import java.util.List;

public interface MenuService {


    public List<MenuVO> list();
    public MenuVO detail(MenuVO menuVO);
    public int insert(MenuVO menuVO);
    public int update(MenuVO menuVO);
    public int delete(MenuVO menuVO);
    public int updateDel(MenuVO menuVO);
}
