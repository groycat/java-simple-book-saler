package cn.mayun.book.util;

import cn.mayun.book.model.User;

import javax.servlet.http.HttpSession;

public class Auth {

    public static User getAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return null;
        }
        if (user.getIsAdmin() == 1) {
            return user;
        }
        return null;
    }

}
