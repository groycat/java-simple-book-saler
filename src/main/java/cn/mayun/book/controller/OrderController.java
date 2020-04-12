package cn.mayun.book.controller;

import cn.mayun.book.dao.OrderDAO;
import cn.mayun.book.model.Order;
import cn.mayun.book.model.User;
import cn.mayun.book.util.Auth;
import cn.mayun.book.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {

    @Autowired
    private OrderDAO orderDAO;

    @RequestMapping("/order/buy/{bid}")
    public String add(@PathVariable("bid") Integer bid,
                      @RequestParam String name,
                      @RequestParam String phone,
                      @RequestParam String addr,
                      ModelMap modelMap,
                      HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "login";
        }

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(addr)) {
            return Result.fail(modelMap, "请提供所有收货信息");
        }

        Order order = new Order();
        order.setAddress(addr);
        order.setPhone(phone);
        order.setAcceptName(name);
        order.setUid(user.getId());
        order.setBid(bid);

        if (orderDAO.add(order)) {
            return Result.suc(modelMap, "下单成功");
        } else {
            return Result.fail(modelMap, "下单失败");
        }
    }

    @RequestMapping("/admin/order/confirm/{id}")
    public String confirm(ModelMap modelMap,
                          HttpSession session,
                          @PathVariable("id") Integer id) {
        if (Auth.getAdmin(session) == null) {
            return Result.auth(modelMap);
        }

        if (orderDAO.confirm(id)) {
            return Result.adminSuc(modelMap, "确认成功");
        }
        return Result.adminFail(modelMap, "确认失败");
    }

    @RequestMapping("/order/done/{id}")
    public String done(ModelMap modelMap,
                       @PathVariable("id") Integer id) {
        if (orderDAO.done(id)) {
            return Result.suc(modelMap, "确认成功");
        }
        return Result.fail(modelMap, "确认失败");
    }

    @RequestMapping("/admin/order/list")
    public String listAdmin(ModelMap modelMap,
                            HttpSession session) {
        if (Auth.getAdmin(session) == null) {
            return Result.auth(modelMap);
        }

        modelMap.put("orders", orderDAO.listAll());
        return "admin_order";
    }

    @RequestMapping("/order/my")
    public String list(ModelMap modelMap,
                       HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "login";
        }

        modelMap.put("orders", orderDAO.list(user.getId()));
        System.out.println(modelMap.get("orders"));

        return "orders";
    }

}
