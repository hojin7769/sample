package com.example.sample.web;

import com.example.sample.model.PropertiesForm;
import com.example.sample.service.CommonService;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private CommonService commonService;

    @GetMapping("/list")
    public String test(Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> list = commonService.list("BoardMapper.list", map);

        model.addAttribute("list", list);

        return "board/list";
    }

    @GetMapping(value = { "/detail", "/detail/{seq}" })
    public String detail(@PathVariable(required = false) Integer seq, Model model) {
        if (seq == null) {
            seq = 0;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        if (seq > 0) {
            map.put("NO_SEQ", seq);
            map = commonService.detail("BoardMapper.detail", map);
        } else {
            map.put("NO_SEQ", seq);
        }

        PropertiesForm form = new PropertiesForm();
        form.setProperties(map);

        model.addAttribute("form", form);
        model.addAttribute("seq", seq);
        return "board/detail";
    }


    @PostMapping("/save")
    public String save(Model model, @ModelAttribute("form") PropertiesForm form, RedirectAttributes redirectAttributes) throws Exception {
        Map<String, Object> map = form.getProperties();

        Integer seq = MapUtils.getInteger(map, "NO_SEQ");

        model.addAttribute("form", form);

        if (seq > 0) {
            commonService.update("BoardMapper.update", map);
        } else {
            commonService.insert("BoardMapper.insert", map);
        }

        return "redirect:/board/detail/" + MapUtils.getString(map, "NO_SEQ");
    }

    @PostMapping("/delete")
    public String delete(Model model, @ModelAttribute("form") PropertiesForm form,
                         RedirectAttributes redirectAttributes) {
        Map<String, Object> map = form.getProperties();

        model.addAttribute("form", form);

        String mapperId = "BoardMapper.delete";

        commonService.delete(mapperId, map);

        redirectAttributes.addFlashAttribute("message", "삭제 되었습니다.");
        return "redirect:/board/list";
    }

}
