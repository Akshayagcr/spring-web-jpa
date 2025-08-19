DROP TABLE IF EXISTS app_user;
DROP TYPE IF EXISTS Gender;

CREATE TYPE Gender AS ENUM ('MALE', 'FEMALE', 'OTHER');

CREATE TABLE app_user(
id SERIAL,
version BIGINT,
first_name VARCHAR (50) NOT NULL,
last_name VARCHAR (50) NOT NULL,
email VARCHAR (255) UNIQUE NOT NULL,
gender Gender,
city VARCHAR (50),
country VARCHAR (50),
created_at TIMESTAMP NOT NULL DEFAULT now(),
updated_at TIMESTAMP,

PRIMARY KEY(id)
);