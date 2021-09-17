package pers.kaslufl.springboot.model.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import pers.kaslufl.springboot.model.entity.Book;

import java.util.List;

public class BookRepository {

    private JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Book search(int id) {
        return jdbcTemplate.queryForObject("select * from book where id = ?", new Object[]{id}, new BookMapper());
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

    public Book update(Book book) {
        String sql = "update book set title = ?, author = ?, publisher = ?, release_date = ?, isbn = ?, topic =? where id = ?";
        int update = jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getReleaseDate(),
                book.getIsbn(),
                book.getTopic(),
                book.getId());

        if( update == 1 ) {
            System.out.println("Book: " + book.getTitle() + " was updated!");
        }
        return book;
    }
    public void delete(int id) {
        String sql = "delete from book where id = ?";
        int delete = jdbcTemplate.update(sql,id);
        if( delete == 1 ) {
            System.out.println("Book: " + id + " was deleted.");
        }
    }
}
