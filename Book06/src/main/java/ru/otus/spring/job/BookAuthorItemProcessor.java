package ru.otus.spring.job;

import org.springframework.batch.item.ItemProcessor;
import ru.otus.spring.domain.BookAuthor;
import ru.otus.spring.domain.BookAuthorMongo;

public class BookAuthorItemProcessor implements  ItemProcessor<BookAuthor, BookAuthorMongo> {

    @Override
    public BookAuthorMongo process(BookAuthor item) throws Exception {

        BookAuthorMongo bookAuthorMongo = new BookAuthorMongo();
        bookAuthorMongo.setAuthorId(String.valueOf(item.getAuthor().getId()));
        bookAuthorMongo.setBookId(String.valueOf(item.getBook().getId()));
        return bookAuthorMongo;
    }
}

