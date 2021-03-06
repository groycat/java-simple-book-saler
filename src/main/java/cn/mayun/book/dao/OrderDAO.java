package cn.mayun.book.dao;

import cn.mayun.book.dao.mapper.OrderMapper;
import cn.mayun.book.enums.BookStatusEnum;
import cn.mayun.book.enums.OrderStatusEnum;
import cn.mayun.book.model.Book;
import cn.mayun.book.model.Order;
import cn.mayun.book.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class OrderDAO {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private UserDAO userDAO;

    public boolean add(Order order) {
        Book book = bookDAO.get(order.getBid());
        if (book == null) {
            return false;
        }

        String sql = "INSERT `order`(uid,create_date,price,phone,address,status,accept_name,bid) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        int aff = template.update(sql,
                order.getUid(),
                new Date(),
                book.getPrice(),
                order.getPhone(),
                order.getAddress(),
                OrderStatusEnum.UnConfirm.getId(),
                order.getAcceptName(),
                order.getBid());

        if (aff == 1) {
            // 更改book的状态
            return bookDAO.updateStatus(order.getBid(), BookStatusEnum.Sold.getId());
        }
        return false;
    }

    public List<Order> list(int uid) {
        String sql = "SELECT id,uid,accept_name,create_date,phone,address,bid,price,status " +
                "FROM `order` " +
                "WHERE uid=? AND `status`!=?";
        List<Order> orders = template.query(sql, new OrderMapper(),
                uid, OrderStatusEnum.Done.getId());
        // 这里需要为订单设置书本标题
        for (Order order : orders) {
            Book book = bookDAO.getById(order.getBid());
            order.setTitle(book.getTitle());
        }

        return orders;
    }

    public List<Order> listAll() {
        String sql = "SELECT id,uid,accept_name,create_date,phone,address,bid,price,status FROM `order`";
        List<Order> orders = template.query(sql, new OrderMapper());
        for (Order order : orders) {
            Book book = bookDAO.getById(order.getBid());
            order.setTitle(book.getTitle());

            User user = userDAO.getById(order.getUid());
            order.setUserName(user.getName());
        }
        return orders;
    }

    public boolean confirm(int id) {
        String sql = "UPDATE `order` SET status=? WHERE id=?";
        int affect = template.update(sql, OrderStatusEnum.Confirm.getId(), id);
        return affect == 1;
    }

    public boolean done(int id) {
        String sql = "UPDATE `order` SET status=? WHERE id=?";
        int affect = template.update(sql, OrderStatusEnum.Done.getId(), id);
        return affect == 1;
    }

}
