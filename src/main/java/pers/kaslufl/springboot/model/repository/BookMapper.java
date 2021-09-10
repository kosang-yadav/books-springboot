package pers.kaslufl.springboot.model.repository;

import org.springframework.jdbc.core.RowMapper;
import pers.kaslufl.springboot.model.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setPublisher(resultSet.getString("publisher"));
        book.setReleaseDate(resultSet.getString("release_date"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setTopic(resultSet.getString("topic"));
        return book;
    }
}
