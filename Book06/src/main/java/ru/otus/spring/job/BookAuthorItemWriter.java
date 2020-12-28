package ru.otus.spring.job;

import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.domain.BookAuthorMongo;
import ru.otus.spring.repository.BookAuthorMongoRepository;

public class BookAuthorItemWriter implements ItemWriter<BookAuthorMongo> {


    @Autowired
    private BookAuthorMongoRepository bookAuthorMongoRepository;

    @Override
    public void write(List<? extends BookAuthorMongo> list) throws Exception {
        bookAuthorMongoRepository.saveAll(list);
    }
}
