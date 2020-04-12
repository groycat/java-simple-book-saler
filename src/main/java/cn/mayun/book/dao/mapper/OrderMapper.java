package cn.mayun.book.dao.mapper;

import cn.mayun.book.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setUid(resultSet.getInt("uid"));
        order.setAcceptName(resultSet.getString("accept_name"));
        order.setCreateDate(resultSet.getDate("create_date"));
        order.setPhone(resultSet.getString("phone"));
        order.setAddress(resultSet.getString("address"));
        order.setBid(resultSet.getInt("bid"));
        order.setPrice(resultSet.getInt("price"));
        order.setStatus(resultSet.getInt("status"));
        return order;
    }
}
