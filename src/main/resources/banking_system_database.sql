DROP SCHEMA banking_system_database;
CREATE SCHEMA IF NOT EXISTS banking_system_database;
USE banking_system_database ;

CREATE TABLE IF NOT EXISTS `user` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  username VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (id));


CREATE TABLE IF NOT EXISTS account_holder (
  date_of_birth DATETIME(6) NULL DEFAULT NULL,
  city VARCHAR(255) NULL DEFAULT NULL,
  street VARCHAR(255) NULL DEFAULT NULL,
  account_holder_id BIGINT NOT NULL,
  PRIMARY KEY (account_holder_id),
    FOREIGN KEY (account_holder_id) REFERENCES user (id));


CREATE TABLE IF NOT EXISTS account (
  id BIGINT NOT NULL AUTO_INCREMENT,
  amount_balance DECIMAL(19,2) NULL DEFAULT NULL,
  currency_balance VARCHAR(255) NULL DEFAULT NULL,
  creation_date DATETIME(6) NULL DEFAULT NULL,
  penalty_fee DECIMAL(19,2) NULL DEFAULT NULL,
  primary_owner BIGINT NULL DEFAULT NULL,
  secondary_owner BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (secondary_owner) REFERENCES account_holder (account_holder_id),
    FOREIGN KEY (primary_owner) REFERENCES account_holder (account_holder_id));


CREATE TABLE IF NOT EXISTS admin (
  admin_id BIGINT NOT NULL,
  PRIMARY KEY (admin_id),
    FOREIGN KEY (admin_id) REFERENCES user (id));


CREATE TABLE IF NOT EXISTS checking (
  amount_minimum_balance DECIMAL(19,2) NULL DEFAULT NULL,
  currency_minimum_balance VARCHAR(255) NULL DEFAULT NULL,
  monthly_maintenance_fee DECIMAL(19,2) NULL DEFAULT NULL,
  secret_key VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  checking_id BIGINT NOT NULL,
  PRIMARY KEY (checking_id),
    FOREIGN KEY (checking_id) REFERENCES account (id));


CREATE TABLE IF NOT EXISTS credit_card (
  amount_credit_limit DECIMAL(19,2) NULL DEFAULT NULL,
  currency_credit_limit VARCHAR(255) NULL DEFAULT NULL,
  interest_rate DECIMAL(19,2) NULL DEFAULT NULL,
  credit_card_id BIGINT NOT NULL,
  PRIMARY KEY (credit_card_id),
    FOREIGN KEY (credit_card_id) REFERENCES account (id));


CREATE TABLE IF NOT EXISTS `role` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  user_id BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id));


CREATE TABLE IF NOT EXISTS savings (
  interest_rate DECIMAL(19,2) NULL DEFAULT NULL,
  amount_minimum_balance DECIMAL(19,2) NULL DEFAULT NULL,
  currency_minimum_balance VARCHAR(255) NULL DEFAULT NULL,
  secret_key VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  savings_id BIGINT NOT NULL,
  PRIMARY KEY (savings_id),
    FOREIGN KEY (savings_id) REFERENCES account (id));


CREATE TABLE IF NOT EXISTS student_checking (
  secret_key VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  student_checking_id BIGINT NOT NULL,
  PRIMARY KEY (student_checking_id),
    FOREIGN KEY (student_checking_id) REFERENCES account (id));


INSERT INTO `user` (username, `password`) VALUES
("accountholder", "$2a$10$MSzkrmfd5ZTipY0XkuCbAejBC9g74MAg2wrkeu8/m1wQGXDihaX3e"),
("admin","$2a$10$MSzkrmfd5ZTipY0XkuCbAejBC9g74MAg2wrkeu8/m1wQGXDihaX3e");

INSERT INTO `role` (name, user_id) VALUES
("ACCOUNTHOLDER", null),
("ADMIN", null);

SELECT * FROM `user`; 

DROP TABLE account_holder;

INSERT INTO account_holder( date_of_birth, city, street, account_holder_id) VALUES
  ('1997-7-12 23:54:10', "Calles", "C/Empedrado", 1),
  ('2000-8-4 10:15:10', "Calles", "C/Empedrado", 2);
  
INSERT INTO account (amount_balance, currency_balance, creation_date, penalty_fee, primary_owner, secondary_owner) VALUES
(12345, "Euro", '2021-11-17 11:55:34', 0.12, "1", "2");
  