package ru.otus.spring.listener;

import java.util.List;
import org.springframework.batch.core.ItemWriteListener;
import ru.otus.spring.domain.BookAuthorMongo;

public class BookAuthorItemWriterListener implements ItemWriteListener<BookAuthorMongo> {

    @Override
    public void beforeWrite(List<? extends BookAuthorMongo> list) {
        System.out.println("beforeWrite");
    }

    @Override
    public void afterWrite(List<? extends BookAuthorMongo> list) {
        for (BookAuthorMongo bookAuthorMongo : list) {
            System.out.println("afterWrite :" + bookAuthorMongo.toString());
        }
    }

    @Override
    public void onWriteError(Exception e, List<? extends BookAuthorMongo> list) {
        System.out.println("onWriteError");
    }
}
