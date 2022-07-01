package com.example.sample.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.service.CommonService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CommonApi {

    private final Logger LOGGER = LoggerFactory.getLogger(CommonApi.class);

    @Autowired
    private CommonService commonService;


    @PostMapping("/list")
    public List<Map<String, Object>> list(@RequestBody Map<String, Object> param) {
        String mapperId = MapUtils.getString(param, "MapperId");
        return commonService.list(mapperId, param);
    }

    @PostMapping("/detail")
    public Map<String, Object> detail(@RequestBody Map<String, Object> param) {
        String mapperId = MapUtils.getString(param, "MapperId");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", commonService.detail(mapperId, param));
        return map;
    }

    @PostMapping("/save")
    public Map<String, Object> save(@RequestBody Map<String, Object> param) {
        String mapperId = MapUtils.getString(param, "MapperId");

        Map<String, Object> map = new HashMap<String, Object>();
        if (mapperId.endsWith(".insert")) {
            map.put("data", commonService.insert(mapperId, param));
        } else if (mapperId.endsWith(".update")) {
            map.put("data", commonService.update(mapperId, param));
        } else if (mapperId.endsWith(".delete")) {
            map.put("data", commonService.delete(mapperId, param));
        }
        return map;
    }
}
