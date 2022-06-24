package com.example.sample.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.sample.model.BoardVO;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.service.BoardService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class BoardApi {
	
	@Autowired
	private BoardService boardService;

	@GetMapping("/test")
	public Map test() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("AA", "test");
		map.put("BB", 1234);
		return map;
	}

	@PostMapping("/boardList")
	public List<BoardVO> list(@RequestBody(required = false) BoardVO param) {
		return boardService.list();
	}


	@PostMapping("/boardDetail")
	public Map<String, Object> detail(@RequestBody BoardVO param) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", boardService.detail(param));
		return map;
	}

	@PostMapping("/boardSave")
	public Map<String, Object> save(@RequestBody BoardVO param) {

//		Integer no_seq = MapUtils.getInteger(param, "NO_SEQ");

		Map<String, Object> map = new HashMap<String, Object>();
		if (param.getNO_SEQ() > 0) {
			map.put("data", boardService.update(param));
		} else {
			map.put("data", boardService.insert(param));
		}
		return map;
	}

	@PostMapping("/boardDelete")
	public Map<String, Object> delete(@RequestBody BoardVO param) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", boardService.delete(param));
		return map;
	}
}
