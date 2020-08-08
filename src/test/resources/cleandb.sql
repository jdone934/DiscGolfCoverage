DROP TABLE IF EXISTS favorite_courses;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS favorite_tournaments;
DROP TABLE IF EXISTS favorite_rounds;
DROP TABLE IF EXISTS favorite_players;
DROP TABLE IF EXISTS tournament_at_course;
DROP TABLE IF EXISTS rounds_in_tournament;
DROP TABLE IF EXISTS players_in_round;
DROP TABLE IF EXISTS commentators;
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
    `location_country` varchar(60),
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
    `profile_picture` varchar(200),
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

CREATE TABLE commentators (
    `playerId` int(10) NOT NULL,
    `roundId` int(10) NOT NULL,
    PRIMARY KEY (`playerId`, `roundId`),
    FOREIGN KEY (`playerId`)
        REFERENCES `player` (`playerId`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`roundId`)
        REFERENCES `round` (`roundId`)
        ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO
    round (roundId, round_number, coverage_link, coverage_provider)
    VALUES (1, 2, 'testLink', 'JomezPro');

INSERT INTO
    player (playerId, first_name, last_name, profile_picture)
    VALUES (1, 'Ricky', 'Wysocki', 'RickyWysocki.jpg'),
           (2, 'Paul', 'McBeth', 'PaulMcBeth.jpg'),
           (3, 'Reid', 'Frescura', 'ReidFrescura.jpg'),
           (4, 'Kevin', 'Jones', 'KevinJones.jpg'),
           (5, 'Jeremy', 'Koling', 'JeremyKoling.jpg'),
           (6, 'Nate', 'Perkins', 'NatePerkins.jpg');

INSERT INTO
    tournament (tournamentId, name, year, tournamentSeries, website)
    VALUES (1, 'Ledgestone Insurance Open', 2019, '', '');

INSERT INTO
    course (courseId, name, location_city, location_state, location_country, website)
    VALUES (1, 'Ledgestone', 'Eureka', 'IL', 'US', '');

INSERT INTO
    round (roundId, round_number, coverage_link, coverage_provider)
    VALUES (2, 3, 'https://www.youtube.com/watch?v=h_whNud9KcM&list=PLZ1LrAadOyA0hTObHHKKHf2ezlUho4gDW&index=6&t=0s', 'JomezPro');

INSERT INTO
    tournament_at_course (courseId, tournamentId)
    VALUES (1, 1);

INSERT INTO
    rounds_in_tournament (roundId, tournamentId)
    VALUES (2, 1);

INSERT INTO
    players_in_round (roundId, playerId)
    VALUES (2, 1),
           (2, 2),
           (2, 3),
           (2, 4);

INSERT INTO
    commentators (playerId, roundId)
    VALUES (5, 2),
           (6, 2);