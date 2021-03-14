-- Table users

CREATE TABLE users (
                       id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                       username VARCHAR(255) not null ,
                       surname VARCHAR(255),
                       email VARCHAR(255) NOT NULL ,
                       password VARCHAR(255) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ;

-- Table roles

CREATE TABLE roles (
                       id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                       name VARCHAR(255) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ;

-- Table users_roles

CREATE TABLE users_roles (
                             user_id BIGINT NOT NULL ,
                             role_id INT NOT NULL,

                             FOREIGN KEY (user_id) REFERENCES users (id),
                             FOREIGN KEY (role_id) REFERENCES roles (id),

                             UNIQUE (user_id, role_id)

) ENGINE = InnoDB DEFAULT CHARSET=utf8 ;

INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users (username, password, email) VALUES ('user', '$2y$12$ndJdQGEynAPcyH50Tad8Rema64OLco7dlHME3YMG/zsLOaJk0b8X6', 'user@gmail.com');

INSERT INTO users (username, password, email) VALUES ('admin', '$2y$12$/9Pa99JKDGNR0Ry84fqtl.1PnLzJrOWDFXWOW39cMRuTZZAb/6iSi', 'admin@gmail.com');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1);