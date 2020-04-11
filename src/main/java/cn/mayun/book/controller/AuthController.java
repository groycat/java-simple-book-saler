package cn.mayun.book.controller;

import cn.mayun.book.model.User;
import cn.mayun.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/test")
    public String login(String name, String password, ModelMap modelMap) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            modelMap.put("info", "参数错误");
            return "test";
        }

        User user = userService.login(name, password);
        if (user == null) {
            modelMap.put("info", "密码错误");
        } else{
            modelMap.put("info", "登录成功");
            modelMap.put("user", user);
        }

        return "test";
    }
}
