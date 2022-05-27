DROP DATABASE IF EXISTS bullcowsDB;

CREATE DATABASE bullcowsDB;

USE bullcowsDB;

CREATE TABLE Game(
	GameId INT primary key auto_increment,
	Answer INT NOT NULL,
    Finished boolean default false
);

drop table Rounds;
CREATE TABLE Rounds(
	RoundId INT primary key auto_increment,
	PartialWins INT,
    ExactWins INT,
    GuessTime DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    GameId INT,
    UserGuess INT,
    Results TEXT,
    CONSTRAINT FK_GameRounds foreign key (GameId)
    REFERENCES GAME(GameId)
    
);



SHOW TABLES;