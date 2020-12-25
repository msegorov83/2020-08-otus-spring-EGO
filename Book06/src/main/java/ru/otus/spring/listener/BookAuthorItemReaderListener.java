package ru.otus.spring.listener;

import org.springframework.batch.core.ItemReadListener;
import ru.otus.spring.domain.BookAuthor;

public class BookAuthorItemReaderListener implements ItemReadListener<BookAuthor> {

    @Override
    public void beforeRead() {
        System.out.println("beforeRead");
    }

    @Override
    public void afterRead(BookAuthor bookAuthor) {
        System.out.println("afterRead: " + bookAuthor.toString());
    }

    @Override
    public void onReadError(Exception e) {
        System.out.println("onReadError");
    }
}
