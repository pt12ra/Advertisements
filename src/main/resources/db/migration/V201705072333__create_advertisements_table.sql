CREATE TABLE `advertisements`.`advertisements` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `TITLE` VARCHAR(45) NOT NULL,
  `DESCRIPTION` VARCHAR(45) NULL,
  `PUBLICATION_TIME` TIMESTAMP NULL,
  `PRICE` DECIMAL(19,2) NULL,
  `USER_ID` INT NULL,
  `BOARD_ID` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_advertisements_users_user_id_idx` (`USER_ID` ASC),
  INDEX `fk_advertisements_boards_board_id_idx` (`BOARD_ID` ASC),
  CONSTRAINT `fk_advertisements_users_user_id`
  FOREIGN KEY (`USER_ID`)
  REFERENCES `advertisements`.`users` (`ID`)
    ON DELETE SET NULL
    ON UPDATE SET NULL,
  CONSTRAINT `fk_advertisements_boards_board_id`
  FOREIGN KEY (`BOARD_ID`)
  REFERENCES `advertisements`.`boards` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
