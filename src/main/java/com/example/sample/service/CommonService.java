package com.example.sample.service;

import java.util.List;
import java.util.Map;

public interface CommonService {
    public List<Map<String, Object>> list(String mapperId, Map<String, Object> map);
    public Map<String, Object> detail(String mapperId, Map<String, Object> map);
    public Map<String, Object> insert(String mapperId, Map<String, Object> map);
    public Map<String, Object> update(String mapperId, Map<String, Object> map);
    public Map<String, Object> delete(String mapperId, Map<String, Object> map);
}
