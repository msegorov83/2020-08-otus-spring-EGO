package ru.otus.spring.shell;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookAuthor;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookAuthorRepository;
import ru.otus.spring.repository.BookRepository;

/*Commands for example

demo - тестовые данные для БД
migration - старт миграции

 */

@ShellComponent
public class CommandShell {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookAuthorRepository bookAuthorRepository;

    private final Job importUserJob;
    private final JobLauncher jobLauncher;

    public CommandShell(JobLauncher jobLauncher,
                        Job importUserJob,AuthorRepository authorRepository,
                        BookRepository bookRepository,
                        BookAuthorRepository bookAuthorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookAuthorRepository = bookAuthorRepository;
        this.importUserJob = importUserJob;
        this.jobLauncher = jobLauncher;
    }

    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "migration")
    public void startMigrationJobWithJobLauncher() throws Exception {
        jobLauncher.run(importUserJob, new JobParameters());
    }

    @Transactional
    @ShellMethod(value = "Demo mode", key = {"demo"})
    public String demoMode() {
        authorRepository.save(new Author("Питер Гуральник"));
        var book = new Book("Элвис Пресли. Последний поезд в Мемфис");

        var newBook = bookRepository.save(book);
        BookAuthor bookAuthor = new BookAuthor(newBook,authorRepository.findById(1L));
        bookAuthorRepository.save(bookAuthor);

        return "demo";
    }



 }
