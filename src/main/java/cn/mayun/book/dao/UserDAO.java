package cn.mayun.book.dao;


import cn.mayun.book.dao.mapper.UserMapper;
import cn.mayun.book.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    public boolean addUser(String name, String password) {
        if (getByNameAndPassword(name, password) != null) {
            return false;
        }
        String sql = "INSERT INTO user(name, password, is_admin) VALUES(?,?, 0)";
        int aff = template.update(sql, name, password);
        return aff == 1;
    }

    public boolean changePassword(int uid, String password) {
        String sql = "UPDATE user SET password=? WHERE id=?";
        return template.update(sql, password, uid) == 1;
    }

}
