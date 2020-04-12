package cn.mayun.book.controller;

import cn.mayun.book.dao.BookDAO;
import cn.mayun.book.dao.UserDAO;
import cn.mayun.book.model.User;
import cn.mayun.book.util.MD5;
import cn.mayun.book.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BookDAO bookDAO;

    @RequestMapping("/")
    public String index(ModelMap modelMap,
                        HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "login";
        }

        modelMap.put("books", bookDAO.list());

        return "list";
    }

    @RequestMapping("/user/register")
    public String register(@RequestParam String name,
                           @RequestParam String password,
                           ModelMap modelMap) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            return Result.fail(modelMap, "参数错误");
        }

        if (userDAO.addUser(name, MD5.md5(password))) {
            // 成功
            return Result.suc(modelMap, "注册成功");
        }
        return Result.fail(modelMap, "注册失败，用户名已经被使用");
    }

    @RequestMapping("/user/login")
    public String login(@RequestParam String name,
                        @RequestParam String password,
                        ModelMap modelMap,
                        HttpSession session) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            return Result.fail(modelMap, "参数错误");
        }

        User user = userDAO.getByNameAndPassword(name, MD5.md5(password));
        if (user == null) {
            return Result.fail(modelMap, "登录失败，用户名或密码错误");
        }

        // 登录成功，user落session
        session.setAttribute("user", user);

        modelMap.put("books", bookDAO.list());
        return "list";
    }

    @RequestMapping("/user/changePassword")
    public String changePassword(HttpSession session,
                                 ModelMap modelMap,
                                 @RequestParam String password) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "login";
        }

        if (userDAO.changePassword(user.getId(), MD5.md5(password))) {
            session.removeAttribute("user");
            return Result.suc(modelMap, "修改密码成功，请重新登录！");
        }

        return Result.fail(modelMap, "修改密码失败，请重试");
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "login";
    }

    @RequestMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/user/changePasswordPage")
    public String changePasswordPage() {
        return "change_password";
    }

}
