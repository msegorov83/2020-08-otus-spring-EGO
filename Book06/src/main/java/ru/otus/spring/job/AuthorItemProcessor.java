package ru.otus.spring.job;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.AuthorMongo;
import ru.otus.spring.domain.BookAuthorMongo;
import ru.otus.spring.repository.BookAuthorMongoRepository;

public class AuthorItemProcessor implements ItemProcessor<Author, AuthorMongo> {

    @Autowired
    private BookAuthorMongoRepository bookAuthorMongoRepository;

    @Override
    public AuthorMongo process(Author item) throws Exception {

        AuthorMongo authorMongo = new AuthorMongo();
        authorMongo.setId(String.valueOf(item.getId()));
        authorMongo.setFullName(item.getFullName());


        List<BookAuthorMongo> bookAuthorMongos = item.getBooks().stream().map(bookAuthor -> {
            BookAuthorMongo obj = new BookAuthorMongo();
            String bookId = String.valueOf(bookAuthor.getBook().getId());
            String authorId = String.valueOf(bookAuthor.getAuthor().getId());

            String id = bookAuthorMongoRepository.findIdByBookIdAndAuthorId(bookId, authorId);
            obj.setBookId(bookId);
            obj.setAuthorId(authorId);
            obj.setId(id);

            return obj;
        }).collect(Collectors.toList());

        authorMongo.setBooks(bookAuthorMongos);

        return authorMongo;
    }

}
