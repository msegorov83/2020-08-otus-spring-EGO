package ru.otus.spring.listener;

import java.util.List;
import org.springframework.batch.core.ItemWriteListener;
import ru.otus.spring.domain.BookMongo;


public class BookItemWriterListener implements ItemWriteListener<BookMongo> {

    @Override
    public void beforeWrite(List<? extends BookMongo> list) {
        System.out.println("beforeWrite");
    }

    @Override
    public void afterWrite(List<? extends BookMongo> list) {
        for (BookMongo bookMongo : list) {
            System.out.println("afterWrite :" + bookMongo.toString());
        }
    }

    @Override
    public void onWriteError(Exception e, List<? extends BookMongo> list) {
        System.out.println("onWriteError");
    }
}
