drop table if exists users;
CREATE TABLE users (
                       user_id IDENTITY PRIMARY KEY auto_increment,
                       user_email VARCHAR(255) not null ,
                       user_password VARCHAR(255) not null
)