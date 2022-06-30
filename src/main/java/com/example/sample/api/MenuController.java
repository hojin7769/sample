package com.example.sample.api;

import com.example.sample.model.MenuVO;
import com.example.sample.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
@CrossOrigin(origins = "http://localhost:3000")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/menuList")
    public Iterable<MenuVO> list() {
        return menuService.list(Sort.by("MENU_SEQ").ascending());
    }



}
