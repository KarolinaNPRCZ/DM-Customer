CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       user_email VARCHAR(255) UNIQUE NOT NULL,
                       user_password VARCHAR(255) NOT NULL
);
CREATE SEQUENCE users_SEQ START 1 INCREMENT 1;

CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL

);
INSERT INTO roles (id,name) VALUES (0,'ADMIN');
INSERT INTO roles (id,name) VALUES (1,'USER');

CREATE TABLE user_roles (
                           user_id INT,
                           role_id INT,
                           PRIMARY KEY (user_id, role_id),
                           FOREIGN KEY (user_id) REFERENCES users(id),
                           FOREIGN KEY (role_id) REFERENCES roles(id)
);

