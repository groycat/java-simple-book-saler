package cn.mayun.book.dao.mapper;

import cn.mayun.book.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setTitle(resultSet.getString("title"));
        book.setId(resultSet.getInt("id"));
        book.setComment(resultSet.getString("comment"));
        book.setPrice(resultSet.getInt("price"));
        book.setImgSrc(resultSet.getString("img_src"));
        book.setStatus(resultSet.getInt("status"));
        return book;
    }
}
