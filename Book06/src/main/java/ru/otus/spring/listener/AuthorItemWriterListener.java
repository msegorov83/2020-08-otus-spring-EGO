package ru.otus.spring.listener;

import java.util.List;
import org.springframework.batch.core.ItemWriteListener;
import ru.otus.spring.domain.AuthorMongo;

public class AuthorItemWriterListener implements ItemWriteListener<AuthorMongo> {

    @Override
    public void beforeWrite(List<? extends AuthorMongo> list) {
        System.out.println("beforeWrite");
    }

    @Override
    public void afterWrite(List<? extends AuthorMongo> list) {
        for (AuthorMongo authorMongo : list) {
            System.out.println("afterWrite :" + authorMongo.toString());
        }
    }

    @Override
    public void onWriteError(Exception e, List<? extends AuthorMongo> list) {
        System.out.println("onWriteError");
    }

}
