package pers.kaslufl.springboot.model.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import pers.kaslufl.springboot.model.entity.Book;

import java.util.List;

public class BookRepository {

    private JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAll() {
        return jdbcTemplate.query("select * from book", new BookMapper());
    }

    public Book create(Book book) throws Exception{
        String sql = "insert into book(id,title,author,publisher,release_date,isbn,topic) values (?,?,?,?,?,?,?)";
        int insert = jdbcTemplate.update(sql,
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getReleaseDate(),
                book.getIsbn(),
                book.getTopic());

        if( insert == 1 ) {
            return book;
        }
        throw new Exception("Book has not been created!");
    }
}
