package cn.mayun.book.dao;

import cn.mayun.book.dao.mapper.BookMapper;
import cn.mayun.book.enums.BookStatusEnum;
import cn.mayun.book.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAO {

    @Autowired
    private JdbcTemplate template;

    public boolean add(Book book) {
        String sql = "INSERT INTO book(title, price, img_src, comment, status) " +
                "VALUES (?,?,?,?,?)";
        int aff = template.update(sql, book.getTitle(), book.getPrice(),
                book.getImgSrc(), book.getComment(), book.getStatus());
        return aff == 1;
    }

    public List<Book> list() {
        String sql = "SELECT id,title,comment,price,img_src,status FROM book WHERE status=?";
        return template.query(sql, new BookMapper(), BookStatusEnum.Normal.getId());
    }

    public List<Book> listAll() {
        String sql = "SELECT id,title,comment,price,img_src,status FROM book";
        return template.query(sql, new BookMapper());
    }

    public Book get(int id) {
        String sql = "SELECT id,title,comment,price,img_src,status FROM book WHERE id=? AND status=?";
        try {
            return template.queryForObject(sql, new BookMapper(), id, BookStatusEnum.Normal.getId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Book getById(int id) {
        String sql = "SELECT id,title,comment,price,img_src,status FROM book WHERE id=?";
        try {
            return template.queryForObject(sql, new BookMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean updateStatus(int id, int status) {
        String sql = "UPDATE book SET status=? WHERE id=?";
        return template.update(sql, status, id) == 1;
    }

}
