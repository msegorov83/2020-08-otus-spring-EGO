package ru.otus.spring.listener;

import org.springframework.batch.core.ItemReadListener;
import ru.otus.spring.domain.Author;

public class AuthorItemReaderListener implements ItemReadListener<Author> {

    @Override
    public void beforeRead() {
        System.out.println("beforeRead");
    }

    @Override
    public void afterRead(Author author) {
        System.out.println("afterRead: " + author.toString());
    }

    @Override
    public void onReadError(Exception e) {
        System.out.println("onReadError");
    }
}
