package cn.mayun.book.service.impl;

import cn.mayun.book.dao.UserDAO;
import cn.mayun.book.model.User;
import cn.mayun.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User login(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return userDAO.getByNameAndPassword(name, password);
    }
}
