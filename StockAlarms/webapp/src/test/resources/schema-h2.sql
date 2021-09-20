CREATE TABLE IF NOT EXISTS `users`(
    `user_id` INT(10) AUTO_INCREMENT,
    `first_name`  VARCHAR(45),
    `last_name`  VARCHAR(45),
    `password` VARCHAR(100),
    `email` VARCHAR(45),
    PRIMARY KEY(`user_id`)
);
