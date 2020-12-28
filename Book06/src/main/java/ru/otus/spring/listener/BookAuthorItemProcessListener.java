package ru.otus.spring.listener;

import org.springframework.batch.core.ItemProcessListener;
import ru.otus.spring.domain.BookAuthor;
import ru.otus.spring.domain.BookAuthorMongo;

public class BookAuthorItemProcessListener implements ItemProcessListener<BookAuthor, BookAuthorMongo> {

    @Override
    public void beforeProcess(BookAuthor bookAuthor) {
        System.out.println("beforeProcess");
    }

    @Override
    public void afterProcess(BookAuthor bookAuthor, BookAuthorMongo bookAuthorMongo) {
        System.out.println("afterProcess: " + bookAuthor + " ---> " + bookAuthorMongo);
    }

    @Override
    public void onProcessError(BookAuthor bookAuthor, Exception e) {
        System.out.println("onProcessError");
    }
}
