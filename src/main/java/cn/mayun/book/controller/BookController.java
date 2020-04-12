package cn.mayun.book.controller;

import cn.mayun.book.dao.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author LazyCat
 * @date 2020-04-11 20:42
 */
@Controller
public class BookController {

    @Autowired
    private BookDAO bookDAO;

    @RequestMapping("/listPage")
    public String list(ModelMap modelMap) {
        modelMap.put("books", bookDAO.list());
        return "list";
    }

    @RequestMapping("/book/detail/{bid}")
    public String detail(@PathVariable("bid") Integer bid,
                         ModelMap modelMap) {

        modelMap.put("book", bookDAO.get(bid));

        return "detail";
    }

    @RequestMapping("/book/buy/{bid}")
    public String buy(@PathVariable("bid") Integer bid,
                      ModelMap modelMap,
                      HttpSession session) {
        // 没有登录不允许操作
        if (session.getAttribute("user") == null) {
            return "login";
        }

        modelMap.put("book", bookDAO.get(bid));
        return "checkout";
    }

}
