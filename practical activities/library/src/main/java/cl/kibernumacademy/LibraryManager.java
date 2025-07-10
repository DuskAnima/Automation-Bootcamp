package cl.kibernumacademy;

import java.util.*;

public class LibraryManager {
    private final Map<Integer, Book> booksMap = new HashMap<>();
    private int nextId= 1;
    public Book addBook(String title, String author, int Year) {
        Book book = new Book(nextId++, title, author, Year);
        booksMap.put(book.getId(), book);
        return book;
    }
}
