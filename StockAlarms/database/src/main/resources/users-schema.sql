CREATE TABLE IF NOT EXISTS users
(
    user_id SERIAL PRIMARY KEY,
    first_name varchar(45)  NOT NULL,
    last_name varchar(45)  NOT NULL,
    password varchar(300) NOT NULL,
    email varchar(254)  NOT NULL
    );
