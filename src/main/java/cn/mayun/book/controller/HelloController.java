package cn.mayun.book.controller;

import cn.mayun.book.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/infoFailed")
    public String infoFailed(ModelMap map) {
        return Result.fail(map, "失败！");
    }

    @RequestMapping("/infoSuc")
    public String infoSuc(ModelMap map) {
        return Result.suc(map, "成功！");
    }
}
