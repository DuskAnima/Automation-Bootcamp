package cl.kibernumacademy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class LibraryManagerTest {
    LibraryManager libraryManager;

    @BeforeEach
    void setUp() {
        libraryManager = new LibraryManager();
    }

    @Test 
    void testAddBook() {
        String bookTitle = "1984";
        String bookAuthor = "George Orwell";
        int year = 1949;

        Book book = libraryManager.addBook(bookTitle, bookAuthor, year);
    
        assertNotNull(book, "Book should  not be null");
        assertEquals(bookTitle, book.getTitle());
    }
}
