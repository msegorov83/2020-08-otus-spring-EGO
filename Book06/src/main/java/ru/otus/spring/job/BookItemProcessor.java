package ru.otus.spring.job;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookAuthorMongo;
import ru.otus.spring.domain.BookMongo;
import ru.otus.spring.repository.BookAuthorMongoRepository;
import ru.otus.spring.repository.BookRepository;

public class BookItemProcessor implements ItemProcessor<Book, BookMongo> {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookAuthorMongoRepository bookAuthorMongoRepository;

    @Override
    public BookMongo process(Book item) throws Exception {
        BookMongo bookMongo = new BookMongo();
        bookMongo.setId(String.valueOf(item.getId()));
        bookMongo.setName(item.getName());

        List<BookAuthorMongo> bookAuthorMongos = item.getAuthors().stream().map(bookAuthor -> {
            BookAuthorMongo obj = new BookAuthorMongo();
            String bookId = String.valueOf(bookAuthor.getBook().getId());
            String authorId = String.valueOf(bookAuthor.getAuthor().getId());

            String id = bookAuthorMongoRepository.findIdByBookIdAndAuthorId(bookId, authorId);
            obj.setBookId(bookId);
            obj.setAuthorId(authorId);
            obj.setId(id);

            return obj;
        }).collect(Collectors.toList());

        bookMongo.setAuthorMongos(bookAuthorMongos);

        return bookMongo;
    }
}
