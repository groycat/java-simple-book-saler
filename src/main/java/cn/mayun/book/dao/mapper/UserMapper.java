package cn.mayun.book.dao.mapper;

import cn.mayun.book.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author LazyCat
 * @date 2020-04-11 19:15
 */
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        user.setIsAdmin(resultSet.getInt("is_admin"));
        return user;
    }
}
