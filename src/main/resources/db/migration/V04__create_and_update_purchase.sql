CREATE TABLE purchase (
    id int8 NOT NULL,
    user_id int8 NOT NULL,
    purchased_books int8 NOT NULL,
    amount_to_pay float8 NOT NULL,
    status VARCHAR(10) NOT NULL,
    CONSTRAINT purchase_id PRIMARY KEY (id)
);

ALTER TABLE purchase ADD CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE purchase ADD CONSTRAINT purchased_books_fk FOREIGN KEY (purchased_books) REFERENCES book(id);