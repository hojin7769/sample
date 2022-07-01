package com.example.sample.api;

import com.example.sample.model.MenuVO;
import com.example.sample.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
@CrossOrigin(origins = "http://localhost:3000")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/menuList")
    public List<MenuVO> list() {
        return menuService.list();
    }

    @PostMapping("/menuDetail")
    public MenuVO detail(@RequestBody MenuVO param) {
        return menuService.detail(param);
    }

    @PostMapping("/menuSave")
    public Map<String, Object> save(@RequestBody MenuVO param) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (param.getMENU_NUM() > 0) {
            map.put("data", menuService.update(param));
        } else {
            map.put("data", menuService.insert(param));
        }
        return map;
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestBody MenuVO param) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data",menuService.updateDel(param));
        return map;
    }

}
