

CREATE TABLE Customers
(
  customer_id            INTEGER(19) AUTO_INCREMENT NOT NULL,
  customer_name          VARCHAR(255)    NOT NULL,
  customer_description   VARCHAR(255),
  survey_start_date      VARCHAR(255),
  survey_end_date        VARCHAR(255),
  age_gap_from           VARCHAR(20),
  age_gap_to             VARCHAR(20),
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
  promoter_score         INTEGER(9)    NOT NULL,
  switch_suppliers       SMALLINT      NOT NULL,
  country_code           VARCHAR(5)    NOT NULL,
  region                 VARCHAR(5),
  time_stamp             VARCHAR(30)
);

CREATE TABLE Questions
(
  question_id            INTEGER(19) AUTO_INCREMENT NOT NULL,
  question_name        VARCHAR(255),
  question_overview_name VARCHAR(255),
  question_short_description   VARCHAR(255),
  question_long_description   VARCHAR(255),
  question_text VARCHAR(2000),
  question_type INTEGER(9),
  question_score_formulation INTEGER(9),
  PRIMARY KEY (question_id)
);

INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Appeal", "Appeals to you", "How well or badly does the company appeal to you", "How well or badly does the company appeal to you", "Brands that appeal to consumers are more likely to try to attract, please, stimulate, and provide interest for the consumer — all behaviors of engagement, which is the foundation of relationships. “Appeal” speaks to much more than just what consumers like. “Appeal” is about what draws them in, then keeps them returning and sharing the experience with their networks. This has important implications for creating lasting and meaningful relationships as well as brand advocates, who in turn create sales.", 1, 1);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Uniqueness", "Uniqueness", "How unique or ordinary do you think the company is", "How unique or ordinary do you think the company is compared to other energy providers", "It’s important for a brand to be differentiated from its competitors in order to be able to create a positive image in the consumer’s minds. Without a perceived differentiation especially regarding intangibles and pure commodities like electricity, that have no physical differentiation, consumers have little to rely on when choosing between competing offers.", 1, 2);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Represent", "Represent", "How well or badly do you know what the company stands for", "How well or badly do you know what the company stands for", "A strong brand promise is one that connects your purpose, your positioning, your strategy, your people and your customer experience. It enables you to deliver your brand in a way that connects emotionally with your customers and differentiates your brand.", 1, 2);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Appeal", "Product", "How well or badly do the company service offerings appeal to you", "How well or badly do the company service offerings appeal to you", "As any successful entrepreneur can tell you, the most important component of creating a successful brand  is providing a good product or service. The strength of your products or services forms the foundation for your entire business. The stronger your products or services are – the greater the empire that can be built upon them. But it’s not enough to have a good product or service on hand if you customers aren’t aware of your product or service quality. A big part of building a strong brand rests on whether your customers associate your brand with quality products or services.", 1, 2);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Name", "Name", "How well or badly do you like the name of the company", "How well or badly do you like the name of the company", "Your name is an extension of your brand. Hearing the name of a a brand involves a whole network of associations to that name, which affect how we think and feel about it. Through our experiences, we automatically cultivate positive and negative connotations about certain words. These connotations can influence deeply the way consumers experience your brand’s name and the brand in general. A good brand name creates meaning behind your brand with completely positive connotations.", 1, 2);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Logo", "Logo", "How well or badly do you like the company logo", "How well or badly do you like the company logo", "We are all aware that we make judgements about people based on first impressions all the time. The same applies to brands and businesses. Consumers will make quick judgments about whether or not to do business with you based partly on how professional you appear to them initially. This is where a strong brand logo comes in handy. But having a good logo is about more than just making a good first impression. It can have a lasting impact on your company. A good logo helps your company build trust, attract new customers, stand out from the competition and convey professionalism.", 1, 2);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Modern", "Modern", "The company is modern", "Please rate how you would associate the following items with teh company by stating if you agree or disagree with the following statements: The company is modern", "To be modern relates to how well the brand appeals to current generations; being up to date, growing and facing forward. Using the right ways to connect to its customers and not falling behind in an ever-changing environment.", 2, 3);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Fun", "Fun", "The company is fun", "Please rate how you would associate the following items with teh company by stating if you agree or disagree with the following statements: The company is fun", "Many personal characteristics can be used to differentiate a company form others on the market. Fun is one of these characteristics that have been used successfully by companies to differentiate itself from others, for example in the telecom market.", 2, 3);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Progressive", "Progressive", "The company is progressive", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company is progressive", "Being able to be viewed as a progressive brand in a stagnant market means that you are doing something different from anyone else. An innovative or progressive brand is able to find new ways in appealing to its customers and therefor making a unique connection. ", 2, 3);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Trustworthy", "Trustworthy", "The company is trustworthy", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company is trustworthy", "If the consumer perceives his energy supplier as trustworthy it has a strong and positive image that is likely to lead to customer loyalty. Consumers in the energy sector are generally concerned about trust related issues and for that reason companies need to address matters of trust in their marketing before going into general image building.", 2, 3);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Old fashioned", "Old fashioned", "The company is old fashioned", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company is old fashioned", "Sometimes old fashioned and conservative companies can be perceived as stable and trustworthy. Energy companies perceived as old fashioned are often viewed as well established on the market and therefor successfully managed.", 2, 3);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Reliable", "Supplier is reliable", "The company is reliable", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company is reliable", "All modern societies require energy for their daily life. It is important for customers to be able to count on safe provision of energy. Reliability is a trust related issue as well as a performance related that is reflected in the brand image and builds up customer loyalty.", 2, 3);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Kind to nature", "Kind to Nature", "The company is kind to nature", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company is kind to nature", "Research shows that individuals born after 1980 have a strong affinity towards sustainability, environmental policy and environmentally friendly brands. These individuals and their children are the future consumers for modern energy brands. These consumers are green, image-conscious, informed and suspicious. In order to connect with and appeal to this group of consumers, brands need to understand their way of thinking.", 2, 3);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Experienced energy company", "Experience in the Field", "The company is an experienced energy company", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company is an experienced energy company", "Being perceived by consumers as experienced in what the company does is positive and trustworthy. As stated, trust is important in the energy sector where as the customer dread the idea of powerlessness. Energy providers that are perceived as experienced in the field are viewed as less likely to make failures that would result in a power outage.", 2, 3);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Delivers quality service", "Reflects Quality",  "The company delivers quality service", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company delivers quality service", "Most customers are generally brand loyal. When clients spend money, they are likely to return to a business they know and with which they have a positive association. ", 2, 3);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Corporate social responsibility", "Social Responsibility", "The company is engages in corporate social responsibility", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company is engages in corporate social responsibility", "For an energy company it´s important to be viewed as socially responsible since most energy companies operate in a newly privatized market. The energy companies’ needs to show that they are not going to take advantage of the fact that in most countries there are few available energy companies to choose from. By showing social responsibility in action the company is proving to their customers that they are able to handle the responsibility.", 2, 3);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Offers green energy", "Green brand", "The company offers green energy", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company offers green energy", "Electricity is set apart from many other products due to the potential effect on the environment caused by its production. Green electricity is electricity produced from sources that do not expel damaging emissions into the atmosphere. Therefor electricity can clearly be differentiated if it is made from green sources. The challenge is to communicate the message to the consumer.", 2, 3);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Wide range of products", "Wide range of products", "The company has a wide range of products", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company has a wide range of products", "It can be good to have a variety of products or service offerings that appeal to different groups of people. But it doesn’t necessarily mean that it’s good to have a large product or service offerings; sometimes it’s better to have a limited selection or no selection at all.", 2, 3);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Understands the needs of young people", "Understands All Ages", "The company understands the needs of young people", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company understands the needs of young people", "This construct measures how you are doing with regards to market segmentation and understanding the needs of your customers. The importance of market segmentation is that it allows a business to precisely reach a consumer with specific needs and wants. In the long run, this benefits the company because it is able to use corporate resources more effectively and make better strategic marketing decisions.", 2, 1);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Offers different services at different prices", "Different Offers", "The company offers different services at different prices", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company offers different services at different prices", "Another part of market segmentation and understanding  your customers is knowing that they have different needs and trying to meet those needs by offering them different services at different prices. It has been proposed that the product and pricing variety a brand offers can influence brand quality perceptions, and consequently, affect brand choice. Specifically, brands that offer greater variety of compatible options are perceived as having greater commitment and expertise in the category, which, in turn, enhances their perceived quality and brand loyalty.", 2, 1);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Offers you the chance to buy products from local sources", "Local Products", "The company offers you the chance to buy products from local sources", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company offers you the chance to buy products from local sources", "Selling locally produced energy means you are a partner in the local community. Customers often perceive local products and services as more desirable as they feel that they are contributing to their society by promoting local businesses and local production.", 2, 1);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Tailors its services to their customers", "Taylored Services", "The company tailors its services to their customers", "Please rate how you would associate the following items with the company by stating if you agree or disagree with the following statements: The company tailors its services to their customers", "The importance of service is ever increasing. The idea is prevalent, among academics and managers alike, that first class services increases customer satisfaction and hence encourages customer loyalty and retention. The branding process of electricity can be defined form a service perspective as electricity has many characteristics similar to those of a service, due to its intangible manner.", 2, 1);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Recommend", "", "How likely are you to recommend the company", "How likely are you to recommend the company to a friend/colleague/relative", "As a brand you would like to know how good or bad was the experience of a consumer such that they would - Promote your brand, stay Passive or even Detract from your brand. For this reason the EBBI survey includes the measuring of your brand’s net promoter score.", 3, 0);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Product Variety", "", "Product variety", "How important do you consider the following factors to be for energy providers in general", "", 4, 0);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Customer service", "", "Customer service", "How important do you consider the following factors to be for energy providers in general", "", 4, 0);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Reliability", "", "Reliability", "How important do you consider the following factors to be for energy providers in general", "", 4, 0);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Environmental policy", "", "Environmental policy", "How important do you consider the following factors to be for energy providers in general", "", 4, 0);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Switched energy suppliers", "", "Have you ever switched energy suppliers? (For other reasons than moving)", "Have you ever switched energy suppliers? (For other reasons than moving)", "", 5, 0);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Gender", "", "What is your gender", "What is your gender", "", 5, 0);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Age", "", "How old are you", "How old are you", "", 5, 0);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("", "", "What is the highest degree or level of school you have completed", "What is the highest degree or level of school you have completed? If currently enrolled, highest degree received", "", 5, 0);
INSERT INTO Questions (question_name, question_overview_name, question_short_description, question_long_description, question_text, question_type, question_score_formulation) VALUES("Household income", "", "Household income", "What is your total household income", "", 5, 0);

CREATE TABLE QuestionType
(
  type_id            INTEGER(19) AUTO_INCREMENT NOT NULL,
  type_name          VARCHAR(255)    NOT NULL,
  PRIMARY KEY (type_id)
);

INSERT INTO QuestionType (type_name) VALUES("Opinion");
INSERT INTO QuestionType (type_name) VALUES("Rating");
INSERT INTO QuestionType (type_name) VALUES("Promoter score");
INSERT INTO QuestionType (type_name) VALUES("Importance of products and services");
INSERT INTO QuestionType (type_name) VALUES("Filters");


CREATE TABLE ScoreFormulation
(
  score_id            INTEGER(19) NOT NULL,
  score_name          VARCHAR(255)    NOT NULL,
  PRIMARY KEY (score_id)
);

INSERT INTO ScoreFormulation (score_id, score_name) VALUES(0, "none");
INSERT INTO ScoreFormulation (score_id, score_name) VALUES(1, "segmentation");
INSERT INTO ScoreFormulation (score_id, score_name) VALUES(2, "differentiation");
INSERT INTO ScoreFormulation (score_id, score_name) VALUES(3, "perception");


CREATE TABLE EbbiScore
(
   question_id      INTEGER(19)   NOT NULL,
   score            DECIMAL(5,2)    NOT NULL
)

INSERT INTO EbbiScore (question_id, score) VALUES(1, 4.45);
INSERT INTO EbbiScore (question_id, score) VALUES(2, 3.63);
INSERT INTO EbbiScore (question_id, score) VALUES(3, 3.66);
INSERT INTO EbbiScore (question_id, score) VALUES(4, 3.62);
INSERT INTO EbbiScore (question_id, score) VALUES(5, 3.55);
INSERT INTO EbbiScore (question_id, score) VALUES(6, 3.56);
INSERT INTO EbbiScore (question_id, score) VALUES(7, 4.30);
INSERT INTO EbbiScore (question_id, score) VALUES(8, 3.38);
INSERT INTO EbbiScore (question_id, score) VALUES(9, 4.31);
INSERT INTO EbbiScore (question_id, score) VALUES(10, 4.48);
INSERT INTO EbbiScore (question_id, score) VALUES(11, 2.90);
INSERT INTO EbbiScore (question_id, score) VALUES(12, 4.58);
INSERT INTO EbbiScore (question_id, score) VALUES(13, 4.01);
INSERT INTO EbbiScore (question_id, score) VALUES(14, 4.16);
INSERT INTO EbbiScore (question_id, score) VALUES(15, 4.43);
INSERT INTO EbbiScore (question_id, score) VALUES(16, 4.08);
INSERT INTO EbbiScore (question_id, score) VALUES(17, 4.41);
INSERT INTO EbbiScore (question_id, score) VALUES(18, 3.93);
INSERT INTO EbbiScore (question_id, score) VALUES(19, 4.01);
INSERT INTO EbbiScore (question_id, score) VALUES(20, 3.80);
INSERT INTO EbbiScore (question_id, score) VALUES(21, 3.90);
INSERT INTO EbbiScore (question_id, score) VALUES(22, 4.35);