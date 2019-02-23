package com.library.scloud.learn.provider.book.service;

import com.library.scloud.learn.provider.book.model.Book;
import org.springframework.stereotype.Component;

/**
 * 图书 业务类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-02-21
 */
@Component
public class BookService {

    public Book choose(Integer bookId) {
        return new Book(bookId, "《人生不设限》", "力克•胡哲");
    }
}