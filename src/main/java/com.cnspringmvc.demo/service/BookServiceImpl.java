package com.cnspringmvc.demo.service;

import com.cnspringmvc.demo.domain.Book;
import com.cnspringmvc.demo.domain.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    
    /*
     * this implementation is not thread-safe
     */
    private List<Category> categories;
    private List<Book> books;
    
    public BookServiceImpl() {
        categories = new ArrayList<Category>();
        Category category1 = new Category(1, "Computing");
        Category category2 = new Category(2, "Travel");
        Category category3 = new Category(3, "Health");
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
        
        books = new ArrayList<Book>();
        books.add(new Book(1L, "9780980839623", 
                "Servlet & JSP: A Tutorial", 
                category1, "Budi Kurniawan"));
        books.add(new Book(2L, "9780980839630",
                "C#: A Beginner's Tutorial",
                category1, "Jayden Ky"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }
    
    @Override
    public Category getCategory(int id) {
        for (Category category : categories) {
            if (id == category.getId()) {
                return category;
            }
        }
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    /**
     * 算法：先将 id 指向最大 id+1，后存入list
     * @param book
     * @return
     */
    @Override
    public Book save(Book book) {
        book.setId(getNextId());
        books.add(book);
        return book;
    }

    @Override
    public Book get(long id) {
        for (Book book : books) {
            if (id == book.getId()) {
                return book;
            }
        }
        return null;
    }

    /**
     * 先遍历books列表，如果找到被修改的book，就执行List的set方法。
     * 如果没找到，就直接返回？？？
     * @param book
     * @return
     */
    @Override
    public Book update(Book book) {
        int bookCount = books.size();
        for (int i = 0; i < bookCount; i++) {
            Book savedBook = books.get(i);
            if (savedBook.getId() == book.getId()) {
                books.set(i, book);
                return book;
            }
        }
        return book;
    }

    /**
     * 算法：先找到Book列表中 id 最大的，后在最大 id 基础上 +1
     */
    @Override
    public long getNextId() {
        // needs to be locked
        long id = 0L;
        for (Book book : books) {
            long bookId = book.getId();
            if (bookId > id) {
                id = bookId;
            }
        }
        return id + 1;
    }
}
