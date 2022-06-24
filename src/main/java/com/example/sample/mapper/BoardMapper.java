package com.example.sample.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    public List<Map<String,Object>> list();
    public Map<String,Object> detail(Map<String, Object> map);
    public int insert(Map<String,Object> map);
    public int update(Map<String,Object> map);
    public int delete(Map<String,Object> map);
}
