DROP TABLE IF EXISTS round;

CREATE TABLE round (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
);