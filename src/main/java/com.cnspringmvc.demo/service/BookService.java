package com.cnspringmvc.demo.service;

import com.cnspringmvc.demo.domain.Book;
import com.cnspringmvc.demo.domain.Category;

import java.util.List;

public interface BookService {
    
    List<Category> getAllCategories();
    Category getCategory(int id);
    List<Book> getAllBooks();
    Book save(Book book);
    Book update(Book book);
    Book get(long id);
    long getNextId();

}
