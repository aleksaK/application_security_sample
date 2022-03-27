DROP TABLE IF EXISTS persons CASCADE;
CREATE TABLE IF NOT EXISTS `persons` (
  `nickname` VARCHAR(20) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `gender` INTEGER NOT NULL,
  `age` INTEGER NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone_number` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`nickname`)
);
INSERT INTO persons VALUES
  ('user1', 'Alexander', 0, 40, 'user1@mail.com', '+371 11111111'),
  ('user2', 'Tatyana', 1, 32, 'user2@mail.com', '+371 22222222'),
  ('user3', 'Vladimir', 0, 65, 'user3@mail.com', '+371 33333333'),
  ('user4', 'Marina', 1, 25, 'user4@mail.com', '+371 44444444');