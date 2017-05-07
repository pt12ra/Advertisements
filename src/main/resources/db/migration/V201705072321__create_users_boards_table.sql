CREATE TABLE `advertisements`.`users_boards` (
  `USER_ID` INT NOT NULL,
  `BOARD_ID` INT NOT NULL,
  PRIMARY KEY (`USER_ID`, `BOARD_ID`),
  INDEX `fk_users_boards_boards_board_id_idx` (`BOARD_ID` ASC),
  CONSTRAINT `fk_users_boards_users_user_id`
  FOREIGN KEY (`USER_ID`)
  REFERENCES `advertisements`.`users` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_boards_boards_board_id`
  FOREIGN KEY (`BOARD_ID`)
  REFERENCES `advertisements`.`boards` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
