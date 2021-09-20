CREATE TABLE IF NOT EXISTS monitored_stocks
(
    monitored_stock_id SERIAL PRIMARY KEY,
	stock_id int NOT NULL REFERENCES stocks (stock_id) ON DELETE CASCADE,
	user_id int NOT NULL REFERENCES users (user_id) ON DELETE CASCADE,
	start_alarm float NOT NULL,
    alarm_type int NOT NULL,
    percentage int NOT NULL
);
