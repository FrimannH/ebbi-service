

CREATE TABLE Customers
(
  customer_id            INTEGER(19) AUTO_INCREMENT NOT NULL,
  customer_name          VARCHAR(255)    NOT NULL,
  customer_description   VARCHAR(255),
  PRIMARY KEY (customer_id)
);


CREATE TABLE Questions
(
  question_id            INTEGER(19) AUTO_INCREMENT NOT NULL,
  question_name          VARCHAR(255)    NOT NULL,
  question_short_description   VARCHAR(255),
  question_long_description   VARCHAR(255),
  PRIMARY KEY (question_id)
);


CREATE TABLE Answers
(
  question_id            INTEGER(19)   NOT NULL,
  customer_id            INTEGER(19)   NOT NULL,
  response               INTEGER(9)    NOT NULL,
  country_code           VARCHAR(5)    NOT NULL,
  region                 VARCHAR(5),
  time_stamp             VARCHAR(30)
);


