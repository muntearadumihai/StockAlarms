CREATE TABLE IF NOT EXISTS stocks
(
    stock_id SERIAL PRIMARY KEY,
    name varchar(45)  NOT NULL,
    value float  NOT NULL
    );
