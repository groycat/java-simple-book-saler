package cn.mayun.book.service;

import cn.mayun.book.model.User;

public interface UserService {

    User login(String name, String password);

}
