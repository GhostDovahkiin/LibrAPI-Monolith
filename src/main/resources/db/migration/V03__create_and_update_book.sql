CREATE TABLE book(
    book_id BIGSERIAL NOT NULL PRIMARY KEY,
    isbn VARCHAR(17) UNIQUE NOT NULL,
    title VARCHAR(50) NOT NULL,
    synopsis VARCHAR(500) NOT NULL,
    author VARCHAR(50) NOT NULL,
    publication_year DATE NOT NULL,
    sell_price DECIMAL(10, 2) NOT NULL,
    available_quantity INTEGER NOT NULL
);