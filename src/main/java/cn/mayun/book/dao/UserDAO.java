package cn.mayun.book.dao;


import cn.mayun.book.dao.mapper.UserMapper;
import cn.mayun.book.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate template;

    public User getByNameAndPassword(String name, String password) {
        String sql = "SELECT id,name,password FROM user WHERE name=? AND password=?";
        try {
            return template.queryForObject(sql, new UserMapper(), name, password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
