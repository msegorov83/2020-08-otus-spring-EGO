insert into authors (full_name) values ('Питер Гуральник');
insert into genres (name) values ('Биография');
insert into genres (name) values ('Другие');
insert into books (name) values ('Элвис Пресли. Последний поезд в Мемфис');

insert into  books_authors (book_id, author_id) values(1,1);
insert into  books_genres (book_id, genre_id) values(1,1);