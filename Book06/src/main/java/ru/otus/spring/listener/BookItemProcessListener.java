package ru.otus.spring.listener;

import org.springframework.batch.core.ItemProcessListener;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookMongo;

public class BookItemProcessListener implements ItemProcessListener<Book, BookMongo> {

    @Override
    public void beforeProcess(Book book) {
        System.out.println("beforeProcess");
    }

    @Override
    public void afterProcess(Book book, BookMongo bookMongo) {
        System.out.println("afterProcess: " + book + " ---> " + bookMongo);
    }

    @Override
    public void onProcessError(Book book, Exception e) {
        System.out.println("onProcessError");
    }
}
