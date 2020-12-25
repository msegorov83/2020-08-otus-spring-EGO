package ru.otus.spring.job;

import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.domain.AuthorMongo;
import ru.otus.spring.repository.AuthorMongoRepository;

public class AuthorItemWriter implements ItemWriter<AuthorMongo> {

    @Autowired
    private AuthorMongoRepository authorMongoRepository;

    @Override
    public void write(List<? extends AuthorMongo> list) throws Exception {
        authorMongoRepository.saveAll(list);
    }
}
