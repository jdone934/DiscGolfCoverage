DROP TABLE IF EXISTS favorite_courses;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS favorite_tournaments;
DROP TABLE IF EXISTS favorite_rounds;
DROP TABLE IF EXISTS favorite_players;
DROP TABLE IF EXISTS tournament_at_course;
DROP TABLE IF EXISTS rounds_in_tournament;
DROP TABLE IF EXISTS players_in_round;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS tournament;
DROP TABLE IF EXISTS round;
DROP TABLE IF EXISTS player;

CREATE TABLE user (
    `userId` int(10) NOT NULL AUTO_INCREMENT,
    `username` varchar(25) NOT NULL,
    `password` varchar(50) NOT NULL,
    `first_name` varchar(50),
    `last_name` varchar(50),
    `email` varchar(320) NOT NULL,
    PRIMARY KEY (`userId`)
);

CREATE TABLE role (
    `roleId` int(10) NOT NULL AUTO_INCREMENT,
    `role_name` varchar(50) NOT NULL,
    `username` varchar(25) NOT NULL,
    `user_id` int(10) NOT NULL,
    PRIMARY KEY (`roleId`),
    FOREIGN KEY (`user_id`)
        REFERENCES `user` (`userId`)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE course (
    `courseId` int(10) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `location_city` varchar(50),
    `location_state` varchar(2),
    `website` varchar(500),
    PRIMARY KEY (`courseId`)
);

CREATE TABLE tournament (
    `tournamentId` int(10) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `year` int(4) NOT NULL,
    `tournamentSeries` varchar(100),
    `website` varchar(500),
    PRIMARY KEY (`tournamentId`)
);

CREATE TABLE round (
    `roundId` int(10) NOT NULL AUTO_INCREMENT,
    `round_number` int(2) NOT NULL,
    `coverage_link` varchar(500) NOT NULL,
    `coverage_provider` varchar(100) NOT NULL,
    PRIMARY KEY (`roundId`)
);

CREATE TABLE player (
    `playerId` int(10) NOT NULL AUTO_INCREMENT,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    PRIMARY KEY (`playerId`)
);

CREATE TABLE favorite_courses (
    `courseId` int(10) NOT NULL,
    `userId` int(10) NOT NULL,
    PRIMARY KEY (`courseId`, `userId`),
    FOREIGN KEY (`courseId`)
        REFERENCES `course` (`courseId`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`userId`)
        REFERENCES `user` (`userId`)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE favorite_tournaments (
    `tournamentId` int(10) NOT NULL,
    `userId` int(10) NOT NULL,
    PRIMARY KEY (`tournamentId`, `userId`),
    FOREIGN KEY (`tournamentId`)
        REFERENCES `tournament` (`tournamentId`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`userId`)
        REFERENCES `user` (`userId`)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE favorite_rounds (
    `roundId` int(10) NOT NULL,
    `userId` int(10) NOT NULL,
    PRIMARY KEY (`roundId`, `userId`),
    FOREIGN KEY (`roundId`)
        REFERENCES `round` (`roundId`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`userId`)
        REFERENCES `user` (`userId`)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE favorite_players (
    `playerId` int(10) NOT NULL,
    `userId` int(10) NOT NULL,
    PRIMARY KEY (`playerId`, `userId`),
    FOREIGN KEY (`playerId`)
        REFERENCES `player` (`playerId`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`userId`)
        REFERENCES `user` (`userId`)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE tournament_at_course (
    `courseId` int(10) NOT NULL,
    `tournamentId` int(10) NOT NULL,
    PRIMARY KEY (`courseId`, `tournamentId`),
    FOREIGN KEY (`courseId`)
        REFERENCES `course` (`courseId`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`tournamentId`)
        REFERENCES `tournament` (`tournamentId`)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE rounds_in_tournament (
    `roundId` int(10) NOT NULL,
    `tournamentId` int(10) NOT NULL,
    PRIMARY KEY (`roundId`, `tournamentId`),
    FOREIGN KEY (`roundId`)
        REFERENCES `round` (`roundId`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`tournamentId`)
        REFERENCES `tournament` (`tournamentId`)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE players_in_round (
    `roundId` int(10) NOT NULL,
    `playerId` int(10) NOT NULL,
    PRIMARY KEY (`roundId`, `playerId`),
    FOREIGN KEY (`roundId`)
        REFERENCES `round` (`roundId`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`playerId`)
        REFERENCES `player` (`playerId`)
        ON UPDATE CASCADE ON DELETE CASCADE
);