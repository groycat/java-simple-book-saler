package cn.mayun.book.controller;

import cn.mayun.book.dao.BookDAO;
import cn.mayun.book.enums.BookStatusEnum;
import cn.mayun.book.model.Book;
import cn.mayun.book.util.Auth;
import cn.mayun.book.util.FileUtil;
import cn.mayun.book.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

@Controller
public class BookController {

    @Autowired
    private BookDAO bookDAO;

    @RequestMapping("/listPage")
    public String list(ModelMap modelMap) {
        modelMap.put("books", bookDAO.list());
        return "list";
    }

    @RequestMapping("/admin/book/list")
    public String listAdmin(ModelMap modelMap,
                            HttpSession session) {
        if (Auth.getAdmin(session) == null) {
            return Result.auth(modelMap);
        }
        modelMap.put("books", bookDAO.listAll());
        return "admin_book";
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

    @RequestMapping("/admin/book/add")
    public String addBook(@RequestParam String title,
                          @RequestParam String price,
                          @RequestParam String comment,
                          @RequestParam(value = "file")MultipartFile file,
                          ModelMap modelMap,
                          HttpSession session,
                          HttpServletRequest req)
            throws FileNotFoundException {

        if (Auth.getAdmin(session) == null) {
            return Result.auth(modelMap);
        }

        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(price)
                || StringUtils.isEmpty(comment)) {
            return Result.adminFail(modelMap, "参数错误");
        }

        Integer priceVal;
        try {
            priceVal = Integer.valueOf(price);
        } catch (NumberFormatException e) {
            return Result.adminFail(modelMap, "价格必须是整数");
        }

        String filename = FileUtil.uploadFile(req, file);
        if (StringUtils.isEmpty(filename)) {
            return Result.adminFail(modelMap, "上传文件失败");
        }

        Book book = new Book();
        book.setTitle(title);
        book.setStatus(BookStatusEnum.Normal.getId());
        book.setPrice(priceVal);
        book.setImgSrc(filename);
        book.setComment(comment);

        if (bookDAO.add(book)) {
            return Result.adminSuc(modelMap, "增加书本成功");
        }
        return Result.adminFail(modelMap, "增加书本失败");
    }

    @RequestMapping("/admin/book/addBookPage")
    public String addBookPage(HttpSession session,
                              ModelMap modelMap) {
        if (Auth.getAdmin(session) == null) {
            return Result.auth(modelMap);
        }
        return "add_book";
    }

}
