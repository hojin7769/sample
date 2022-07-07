package com.example.sample.api;

import com.example.sample.model.MenuVO;
import com.example.sample.service.MenuService;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

        System.out.println(param);

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

    @PostMapping("/createTable")
    public Map<String,Object> createTable(@RequestBody Map<String,Object> param){
        Map<String,Object> map = new HashMap<>();
        String sql = "CREATE TABLE ";
        sql += param.get("TABLE_NAME");
        sql += "(";
        sql += "\n";
        System.out.println("---------------------------------------------------------");
        System.out.println("param" + param);
        Iterator<String> keys = param.keySet().iterator();
        while(keys.hasNext()) {
            String key = keys.next();
            if (key == "TABLE_NAME") {
                continue;
            } else if (key == "Table_List") {
                for (int i = 0 ; i < ((List<Map<String,Object>>)param.get("Table_List")).size() ; i++){
                    sql += ((List<Map<String,Object>>)param.get("Table_List")).get(i).get("colum");
                    sql += " ";
                    sql += ((List<Map<String,Object>>)param.get("Table_List")).get(i).get("value");
                    if ((boolean) ((List<Map<String,Object>>)param.get("Table_List")).get(i).get("null_check") == false) {
                        sql += " ";
                        sql += "NOT NULL";
                    }
                    if(param.get("Table_pk").equals(((List<Map<String,Object>>)param.get("Table_List")).get(i).get("colum"))){
                        sql += " ";
                        sql += "PRIMARY KEY";
                    }
                    sql += ",";
                    sql += "\n";
                }
            }
        }
        sql += ")";
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------");
        map.put("create_table",sql);

        menuService.createTable(map);

        return map;
    }

}
