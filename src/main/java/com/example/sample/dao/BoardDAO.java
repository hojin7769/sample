package com.example.sample.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> list(Map<String, Object> map) {
		String sql = "select * from BT_BOARD order by DT_INSERT desc";
		return jdbcTemplate.queryForList(sql, map);
	}

	public Map<String, Object> detail(Map<String, Object> map) {
		try {
			String sql = "select * from BT_BOARD where NO_SEQ = :NO_SEQ";
			return jdbcTemplate.queryForMap(sql, map);
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public int insert(Map<String, Object> map) {
		String sql = "insert into BT_BOARD (ID_USER, DS_TITLE, DS_CONTENT) values (:ID_USER, :DS_TITLE, :DS_CONTENT) ";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		int rtn = jdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
		
		map.put("NO_SEQ", keyHolder.getKey().intValue());
		return rtn;
	}

	public int update(Map<String, Object> map) {
		String sql = "update BT_BOARD set ID_USER = :ID_USER, DS_TITLE = :DS_TITLE, DS_CONTENT = :DS_CONTENT, DT_UPDATE = getdate() where NO_SEQ = :NO_SEQ";

		return jdbcTemplate.update(sql, map);
	}

	public int delete(Map<String, Object> map) {
		String sql = "delete from BT_BOARD where NO_SEQ = :NO_SEQ";

		return jdbcTemplate.update(sql, map);
	}
	

	public int exeception(Map<String, Object> map) {
		String sql = "update BT_BOARD1 set ID_USER = :ID_USER, DS_TITLE = :DS_TITLE, DS_CONTENT = :DS_CONTENT, DT_UPDATE = getdate() where NO_SEQ = :NO_SEQ";

		return jdbcTemplate.update(sql, map);
	}

}
