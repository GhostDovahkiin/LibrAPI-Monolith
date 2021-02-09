CREATE TABLE book(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    isbn VARCHAR(17) NOT NULL,
    title VARCHAR(50) NOT NULL,
    synopsis VARCHAR(120) NOT NULL,
    author VARCHAR(50) NOT NULL,
    publication_year INTEGER NOT NULL,
    sell_price DECIMAL NOT NULL,
    available_quantity INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id)
);