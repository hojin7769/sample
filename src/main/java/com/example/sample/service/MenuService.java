package com.example.sample.service;

import com.example.sample.model.BoardVO;
import com.example.sample.model.MenuVO;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public interface MenuService {


    public Iterable<MenuVO> list(Sort sort);
    public Optional<MenuVO> detail(MenuVO menuVO);
    public MenuVO insert(MenuVO menuVO);
    public MenuVO update(MenuVO menuVO);
    public MenuVO delete(MenuVO menuVO);
}
