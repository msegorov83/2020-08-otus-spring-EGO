package ru.otus.spring.listener;

import org.springframework.batch.core.ItemProcessListener;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.AuthorMongo;

public class AuthorItemProcessListener implements ItemProcessListener<Author, AuthorMongo> {

    @Override
    public void beforeProcess(Author author) {
        System.out.println("beforeProcess");
    }

    @Override
    public void afterProcess(Author author, AuthorMongo authorMongo) {
        System.out.println("afterProcess: " + author + " ---> " + authorMongo);
    }

    @Override
    public void onProcessError(Author author, Exception e) {
        System.out.println("onProcessError");
    }

}
