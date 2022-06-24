package com.example.sample.api;

import com.example.sample.model.BoardVO;
import com.example.sample.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class BoardApi {

	@Autowired
	private BoardService boardService;

	@PostMapping("/boardList")
	public Iterable<BoardVO> list() {
		return boardService.list(Sort.by("DT_INSERT").descending());
	}


	@PostMapping("/boardDetail")
	public Map<String, Object> detail(@RequestBody BoardVO param) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", boardService.detail(param));
		return map;
	}

	@PostMapping("/boardSave")
	public Map<String, Object> save(@RequestBody BoardVO param) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (param.getNO_SEQ() > 0) {
			param.setDT_UPDATE(LocalDateTime.now());
			map.put("data", boardService.update(param));
		} else {
			param.setDT_INSERT(LocalDateTime.now());
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
