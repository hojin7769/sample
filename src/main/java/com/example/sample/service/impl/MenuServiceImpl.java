package com.example.sample.service.impl;

import com.example.sample.mapper.MenuMapper;
import com.example.sample.model.MenuVO;
import com.example.sample.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuVO> list() {
        return menuMapper.list();
    }

    @Override
    public MenuVO detail(MenuVO menuVO) {
        return menuMapper.detail(menuVO);
    }

    @Override
    @Transactional
    public int insert(MenuVO menuVO) {
        int maxSeq = menuMapper.maxSeq();
        menuVO.setMENU_SEQ(maxSeq+1);
        int num = menuMapper.insert(menuVO);
        return num;
    }

    @Override
    @Transactional
    public int update(MenuVO menuVO) {
        int num = menuMapper.update(menuVO);
        return num;

    }

    @Override
    @Transactional
    public int delete(MenuVO menuVO) {
        int num = menuMapper.delete(menuVO);
        return num;
    }

    @Override
    @Transactional
    public int updateDel(MenuVO menuVO) {
       int num =  menuMapper.updateDel(menuVO);
        return num;
    }

    @Override
    public Map<String, Object> createTable(Map<String, Object> map) {
        Map<String,Object> data =  new HashMap<>();
        data.put("data",menuMapper.createTable(map));
        return data;


    }


}
