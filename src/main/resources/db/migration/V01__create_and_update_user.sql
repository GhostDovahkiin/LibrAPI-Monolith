CREATE TABLE users(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INTEGER NOT NULL,
    phone VARCHAR(15) NOT NULL UNIQUE,
    email VARCHAR(50) NOT NULL UNIQUE,
    sex VARCHAR(50) NOT NULL
);

INSERT INTO users(name, age, phone, email, sex) VALUES ('Pedro', 22, '+558398662912', 'pedro.sousa@phoebus.com.br', 'MASCULINO');
INSERT INTO users(name, age, phone, email, sex) VALUES ('Lucas', 21, '+5583986862912', 'lucas.antonio@phoebus.com.br', 'MASCULINO');