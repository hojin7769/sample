package com.example.sample.service.impl;

import com.example.sample.model.MenuVO;
import com.example.sample.repository.MenuRepository;
import com.example.sample.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Override
    public Iterable<MenuVO> list(Sort sort) {
        return menuRepository.findAll(sort);
    }

    @Override
    public Optional<MenuVO> detail(MenuVO menuVO) {
        return  menuRepository.findById(menuVO.getMENU_SEQ());
    }

    @Override
    public MenuVO insert(MenuVO menuVO) {
        return menuRepository.save(menuVO);
    }

    @Override
    public MenuVO update(MenuVO menuVO) {
        return menuRepository.save(menuVO);
    }

    @Override
    public MenuVO delete(MenuVO menuVO) {
        menuRepository.delete(menuVO);
        return menuVO;
    }
}
