package cn.mayun.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/template")
public class HelloController {

    @RequestMapping("/testPage")
    public String TestPage(ModelMap map) {
        map.put("backendValue", "你好");
        map.put("fontendValue", "我好");
        return "test";
    }
}
