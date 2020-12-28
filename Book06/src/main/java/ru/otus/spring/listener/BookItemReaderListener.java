package ru.otus.spring.listener;

import org.springframework.batch.core.ItemReadListener;
import ru.otus.spring.domain.Book;

public class BookItemReaderListener implements ItemReadListener<Book> {

    @Override
    public void beforeRead() {
        System.out.println("beforeRead");
    }

    @Override
    public void afterRead(Book book) {
        System.out.println("afterRead: " + book.toString());
    }

    @Override
    public void onReadError(Exception e) {
        System.out.println("onReadError");
    }
}
