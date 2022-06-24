package com.example.sample.service;

import java.util.List;
import java.util.Map;

public interface BoardService {

	public List<Map<String, Object>> list(Map<String, Object> map);
	public Map<String, Object> detail(Map<String, Object> map);
	public Map<String, Object> insert(Map<String, Object> map);
	public Map<String, Object> update(Map<String, Object> map);
	public Map<String, Object> delete(Map<String, Object> map);
}
