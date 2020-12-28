package ru.otus.spring.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.AuthorMongo;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookAuthor;
import ru.otus.spring.domain.BookAuthorMongo;
import ru.otus.spring.domain.BookMongo;
import ru.otus.spring.job.AuthorItemProcessor;
import ru.otus.spring.job.AuthorItemReader;
import ru.otus.spring.job.AuthorItemWriter;
import ru.otus.spring.job.BookAuthorItemProcessor;
import ru.otus.spring.job.BookAuthorItemReader;
import ru.otus.spring.job.BookAuthorItemWriter;
import ru.otus.spring.job.BookItemProcessor;
import ru.otus.spring.job.BookItemReader;
import ru.otus.spring.job.BookItemWriter;
import ru.otus.spring.listener.AuthorItemProcessListener;
import ru.otus.spring.listener.AuthorItemReaderListener;
import ru.otus.spring.listener.AuthorItemWriterListener;
import ru.otus.spring.listener.BookAuthorItemProcessListener;
import ru.otus.spring.listener.BookAuthorItemReaderListener;
import ru.otus.spring.listener.BookAuthorItemWriterListener;
import ru.otus.spring.listener.BookItemProcessListener;
import ru.otus.spring.listener.BookItemReaderListener;
import ru.otus.spring.listener.BookItemWriterListener;
import ru.otus.spring.listener.BookJobExecutionListener;


@Configuration
@EnableBatchProcessing
public class JobBatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public AuthorItemReader readerAuthor() {
        return new AuthorItemReader();
    }

    @Bean
    public AuthorItemProcessor processorAuthor() {
        return new AuthorItemProcessor();
    }

    @Bean
    public AuthorItemWriter writerAuthor() {
        return new AuthorItemWriter();
    }

    @Bean
    public BookJobExecutionListener jobExecutionListener() {
        return new BookJobExecutionListener();
    }

    @Bean
    public AuthorItemReaderListener readerAuthorListener() {
        return new AuthorItemReaderListener();
    }

    @Bean
    public AuthorItemProcessListener creditAuthorProcessListener() {
        return new AuthorItemProcessListener();
    }

    @Bean
    public AuthorItemWriterListener writerAuthorListener() {
        return new AuthorItemWriterListener();
    }

    @Bean
    public BookAuthorItemReader readerBookAuthor() {
        return new BookAuthorItemReader();
    }

    @Bean
    public BookAuthorItemWriter writerBookAuthor() {
        return new BookAuthorItemWriter();
    }

    @Bean
    public BookAuthorItemProcessor processorBookAuthor() {
        return new BookAuthorItemProcessor();
    }

    @Bean
    public BookAuthorItemReaderListener readerBookAuthorListener() {
        return new BookAuthorItemReaderListener();
    }

    @Bean
    public BookAuthorItemProcessListener creditBookAuthorProcessListener() {
        return new BookAuthorItemProcessListener();
    }

    @Bean
    public BookAuthorItemWriterListener writerListener() {
        return new BookAuthorItemWriterListener();
    }

    @Bean
    public BookItemReader readerBook() {
        return new BookItemReader();
    }

    @Bean
    public BookItemWriter writerBook() {
        return new BookItemWriter();
    }

    @Bean
    public BookItemProcessor processorBook() {
        return new BookItemProcessor();
    }

    @Bean
    public BookItemReaderListener readerBookListener() {
        return new BookItemReaderListener();
    }

    @Bean
    public BookItemProcessListener creditBookProcessListener() {
        return new BookItemProcessListener();
    }

    @Bean
    public BookItemWriterListener writerBookListener() {
        return new BookItemWriterListener();
    }


    @Bean
    public Job job(@Qualifier("stepAuthor")Step stepAuthor,
                   @Qualifier("stepBookAuthor") Step stepBookAuthor,
                   @Qualifier("stepBook") Step stepBook,
                   BookJobExecutionListener executionListener) {

        Job job = jobBuilderFactory.get("job")
                .listener(executionListener)
                .flow(stepBookAuthor)
                .next(stepBook)
                .next(stepAuthor)
                .end()
                .build();
        return job;
    }

    @Bean
    public Step stepAuthor(AuthorItemReader reader,
                     AuthorItemWriter writer,
                     AuthorItemProcessor processor,
                     AuthorItemReaderListener readerListener,
                     AuthorItemProcessListener processListener,
                     AuthorItemWriterListener writerListener) {

        TaskletStep step = stepBuilderFactory.get("authorStep")
                .<Author, AuthorMongo>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(readerListener)
                .listener(processListener)
                .listener(writerListener)
                .build();
        return step;
    }

    @Bean
    public Step stepBookAuthor(BookAuthorItemReader reader,
                               BookAuthorItemWriter writer,
                               BookAuthorItemProcessor processor,
                               BookAuthorItemReaderListener readerListener,
                               BookAuthorItemProcessListener processListener,
                               BookAuthorItemWriterListener writerListener) {

        TaskletStep step = stepBuilderFactory.get("bookAuthorStep")
                .<BookAuthor, BookAuthorMongo>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(readerListener)
                .listener(processListener)
                .listener(writerListener)
                .build();
        return step;
    }

    @Bean
    public Step stepBook(BookItemReader reader,
                         BookItemWriter writer,
                         BookItemProcessor processor,
                         BookItemReaderListener readerListener,
                         BookItemProcessListener processListener,
                         BookItemWriterListener writerListener) {

        TaskletStep step = stepBuilderFactory.get("bookStep")
                .<Book, BookMongo>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(readerListener)
                .listener(processListener)
                .listener(writerListener)
                .build();
        return step;
    }


}
