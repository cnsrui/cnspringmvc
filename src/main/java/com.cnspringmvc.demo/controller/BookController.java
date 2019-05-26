package com.cnspringmvc.demo.controller;

import com.cnspringmvc.demo.domain.Book;
import com.cnspringmvc.demo.domain.Category;
import com.cnspringmvc.demo.service.BookService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    private static final Log logger = LogFactory.getLog(BookController.class);

    @RequestMapping(value = "/book_input")
    public String inputBook(Model model) {
        List<Category> categories = bookService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("book", new Book());
        return "BookAddForm";
    }

    // URL中的可变参数{id}，可被 @PathVariable long id 直接使用
    @RequestMapping(value = "/book_edit/{id}")
    public String editBook(Model model, @PathVariable long id) {
        List<Category> categories = bookService.getAllCategories();
        model.addAttribute("categories", categories);
        //根据传来的路径变量id值，确定要编辑的是哪个条目
        Book book = bookService.get(id);
        //重新返回给视图
        model.addAttribute("book", book);
        System.out.println("book_edit");
        return "BookEditForm";
    }

    // @ModelAttribute使得在每次调用saveBook()方法时创建book实例。
    // 该book实例拥有表单中的数据，可直接进行处理。
    @RequestMapping(value = "/book_save")
    public String saveBook(@ModelAttribute Book book) {
        Category category = bookService.getCategory(book.getCategory().getId());
        book.setCategory(category);
        bookService.save(book);
        //重定向，避免将数据重复保存
        return "redirect:/book_list";
    }

    // @ModelAttribute使得在每次调用updateBook()方法时创建book实例
    // 由于使用了路径变量，会产生2层路径，所以目前改成了这样/book_edit/book_update
    // 正常应该是 (value = "/book_update")。需要继续研究。
    @RequestMapping(value = "/book_edit/book_update")
    public String updateBook(@ModelAttribute Book book) {
        Category category = bookService.getCategory(book.getCategory().getId());
        book.setCategory(category);
        bookService.update(book);
        System.out.println("book_update");
        return "redirect:/book_list";
    }

    @RequestMapping(value = "/book_list")
    public String listBooks(Model model) {
        logger.info("book_list");
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "BookList";
    }
}