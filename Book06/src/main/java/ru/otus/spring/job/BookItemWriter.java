package ru.otus.spring.job;

import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.domain.BookMongo;
import ru.otus.spring.repository.BookMongoRepository;

public class BookItemWriter implements ItemWriter<BookMongo> {

    @Autowired
    private BookMongoRepository bookMongoRepository;

    @Override
    public void write(List<? extends BookMongo> list) throws Exception {
        bookMongoRepository.saveAll(list);
    }
}
