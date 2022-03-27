DROP TABLE IF EXISTS persons, users, authorities CASCADE;

CREATE TABLE IF NOT EXISTS `persons` (
  `nickname` VARCHAR(20) NOT NULL PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL,
  `gender` INTEGER NOT NULL,
  `age` INTEGER NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone_number` VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS `users` (
	`username` VARCHAR(50) NOT NULL PRIMARY KEY,
	`password` VARCHAR(100) NOT NULL,
	`enabled` BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS `authorities` (
	`username` VARCHAR(50) NOT NULL,
	`authority` VARCHAR(50) NOT NULL,
	CONSTRAINT `fk_authorities_users` FOREIGN KEY(username) REFERENCES users(username)
);

INSERT INTO persons VALUES
  ('user1', 'Alexander', 0, 40, 'user1@mail.com', '+371 11111111'),
  ('user2', 'Tatyana', 1, 32, 'user2@mail.com', '+371 22222222'),
  ('user3', 'Vladimir', 0, 65, 'user3@mail.com', '+371 33333333'),
  ('user4', 'Marina', 1, 25, 'user4@mail.com', '+371 44444444');

INSERT INTO users VALUES
  ('admin', '{bcrypt}$2a$12$KCbxeRnh164V3lNgBOBH.e.JdmFyg0HjGrGKy7oETLuKVyV0bBX26', TRUE),
  ('user1', '{bcrypt}$2a$10$SjH4sPLN7wdEEQygLcm7XOP1VLnFgbNlY0CuzUHfIukmD9Opqj8CS', TRUE),
  ('user2', '{bcrypt}$2a$12$usHOXWGwmz5xxD4iNLzHl.o2mWNW2FJBM8UTNVZwejTkcqbFZRSK', TRUE),
  ('user3', '{bcrypt}$2a$12$ePXtRP8G39gKqrTPjRrElubUlnje0E9aQCLAG9yt4l5FP9nFIirHG', TRUE),
  ('user4', '{bcrypt}$2a$12$msJ//RNxCqXcuW7hqKMv8uNh66U0RQzwwbr8lZ2nwgAGDHTTRfP5K', TRUE);

INSERT INTO authorities VALUES
  ('admin', 'ADMIN'),
  ('user1', 'USER'),
  ('user2', 'USER'),
  ('user3', 'USER'),
  ('user4', 'USER');