package com.yuxeng.display;

import static org.junit.jupiter.api.Assertions.*;

import com.yuxeng.display.bookmodel.Book;
import com.yuxeng.display.bookmodel.BookService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
class BookTests {

  @Resource
  private BookService bookService;

  @Test
  public void addBookTest() {
    Book book = new Book();
    book.setTitle("Test Book");
    book.setAuthor("Test Author");
    book.setCategory_id(1);
    book.setPublisher("Test Publisher");
    book.setQuantity(10);
    book.setCreated_at(new Timestamp(System.currentTimeMillis()));
    boolean result = bookService.addBook(book);
    assertTrue(result);
    assertNotNull(book.getId());
  }

  @Test
  public void getBookTest() {
    Integer bookId = 4;
    Book book = bookService.getBook(bookId);
    assertNotNull(book);
    assertEquals(bookId, book.getId());
  }

  @Test
  public void getAllBooksTest() {
    List<Book> books = bookService.getAllBooks();
    assertNotNull(books);
    assertTrue(!books.isEmpty());
  }

  @Test
  public void deleteBookTest() {
    Integer bookId = 1; // Assuming there's a book with ID 1 in the database
    boolean result = bookService.deleteBook(bookId);
    if(!result){
      System.out.println("删除的书籍不存在");
    }
  }

  @Test
  public void matchBookTest() {
    String keyword = "Test";
    int count = bookService.matchBook(keyword);
    assertTrue(count > 0);
  }

  @Test
  public void queryBookTest() {
    String keyword = "Test";
    List<Book> books = bookService.queryBook(keyword);
    assertNotNull(books);
    assertTrue(!books.isEmpty());
  }

  @Test
  public void editBookTest() {
    Integer bookId = 5;
    Book book = bookService.getBook(bookId);
    assertNotNull(book);

    book.setTitle("Updated Title");
    boolean result = bookService.editBook(book);
    assertTrue(result);

    Book updatedBook = bookService.getBook(bookId);
    assertEquals("Updated Title", updatedBook.getTitle());
  }
}
