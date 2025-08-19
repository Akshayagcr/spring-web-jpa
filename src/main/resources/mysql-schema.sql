DROP TABLE IF EXISTS app_user;

CREATE TABLE app_user(
-- As id is always +ve make it unsigned which increases the range of the datatype.
-- We assign the largest datatype i.e. bigint as it give us functionally infinite range.
-- Primary key is the only field for which the rule of choosing the smallest datatype that can fit all the possible values for a column does not apply !!!
id UNSIGNED BIGINT AUTOINCREMENT,
first_name VARCHAR (50) NOT NULL,
last_name VARCHAR (50) NOT NULL,
email VARCHAR (255) UNIQUE NOT NULL,
gender ENUM ('MALE', 'FEMALE', 'OTHER'),
city VARCHAR (50),
country VARCHAR (50),
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

PRIMARY KEY(id)
);