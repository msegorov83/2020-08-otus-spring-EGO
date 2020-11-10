
create table authors(
    id bigserial,
    full_name varchar(100),
    primary key (id)
);

create table genres(
    id bigserial,
    name varchar(50),
    primary key (id)
);

create table books (
    id bigserial,
    name varchar(50),
    primary key (id));

create table books_authors (
    book_id bigint not null references books (id) on delete cascade,
    author_id bigint not null references authors (id) on delete cascade);

create table books_genres (
    book_id bigint not null references books (id) on delete cascade,
    genre_id bigint not null references genres (id) on delete cascade);







