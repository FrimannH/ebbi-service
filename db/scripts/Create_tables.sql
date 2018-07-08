

CREATE TABLE Customers
(
  customer_id            INTEGER(19) AUTO_INCREMENT NOT NULL,
  customer_name          VARCHAR(255)    NOT NULL,
  customer_description   VARCHAR(255),
  PRIMARY KEY (customer_id)
);



CREATE TABLE Answers
(
  question_id            INTEGER(19)   NOT NULL,
  customer_id            INTEGER(19)   NOT NULL,
  response               INTEGER(9)    NOT NULL,
  gender                 INTEGER(9)    NOT NULL,
  age                    INTEGER(9)    NOT NULL,
  income                 INTEGER(9)    NOT NULL,
  education              INTEGER(9)    NOT NULL,
  switch_suppliers       SMALLINT      NOT NULL,
  country_code           VARCHAR(5)    NOT NULL,
  region                 VARCHAR(5),
  time_stamp             VARCHAR(30)
);

CREATE TABLE Questions
(
  question_id            INTEGER(19) AUTO_INCREMENT NOT NULL,
  question_name          VARCHAR(255)    NOT NULL,
  question_short_description   VARCHAR(255),
  question_long_description   VARCHAR(255),
  PRIMARY KEY (question_id)
);

INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Appeal", "How well or badly does the company appeal to you", "How well or badly does the company appeal to you");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Uniqueness", "How unique or ordinary do you think the company is", "How unique or ordinary do you think the company is compared to other energy providers");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Represent", "How well or badly do you know what the company stands for", "How well or badly do you know what the company stands for");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Appeal", "How well or badly do the company service offerings appeal to you", "How well or badly do the company service offerings appeal to you");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Name", "How well or badly do you like the name of the company", "How well or badly do you like the name of the company");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Logo", "How well or badly do you like the company logo", "How well or badly do you like the company logo");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Modern", "The company is modern", "Please rate how you would associate the following items with teh company by stating if you agree or disagree with the following statements: The company is modern");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Fun", "The company is fun", "Please rate how you would associate the following items with teh company by stating if you agree or disagree with the following statements: The company is fun");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Progressive", "The company is progressive", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company is progressive");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Trustworthy", "The company is trustworthy", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company is trustworthy");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Old fashioned", "The company is old fashioned", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company is old fashioned");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Reliable", "The company is reliable", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company is reliable");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Kind to nature", "The company is kind to nature", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company is kind to nature");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Experienced energy company", "The company is an experienced energy company", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company is an experienced energy company");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Delivers quality service", "The company delivers quality service", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company delivers quality service");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Corporate social responsibility", "The company is engages in corporate social responsibility", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company is engages in corporate social responsibility");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Offers green energy", "The company offers green energy", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company offers green energy");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Wide range of products", "The company has a wide range of products", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company has a wide range of products");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Understands the needs of young people", "The company understands the needs of young people", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company understands the needs of young people");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Offers different services at different prices", "The company offers different services at different prices", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company offers different services at different prices");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Offers you the chance to buy products from local sources", "The company offers you the chance to buy products from local sources", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company offers you the chance to buy products from local sources");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Tailors its services to their customers", "The company tailors its services to their customers", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company tailors its services to their customers");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Recommend", "How likely are you to recommend the company", "How likely are you to recommend the company to a friend/colleague/relative");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Product Variety", "Product variety", "How important do you consider the following factors to be for energy providers in general");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Customer service", "Customer service", "How important do you consider the following factors to be for energy providers in general");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Reliability", "Reliability", "How important do you consider the following factors to be for energy providers in general");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("nvironmental policy", "Environmental policy", "How important do you consider the following factors to be for energy providers in general");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Switched energy suppliers", "Have you ever switched energy suppliers? (For other reasons than moving)", "Have you ever switched energy suppliers? (For other reasons than moving)");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Gender", "What is your gender", "What is your gender");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Age", "How old are you", "How old are you");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("", "What is the highest degree or level of school you have completed", "What is the highest degree or level of school you have completed? If currently enrolled, highest degree received");
INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES("Household income", "Household income", "What is your total household income");

