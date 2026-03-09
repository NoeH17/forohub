CREATE TABLE usuarios (

    id BIGINT NOT NULL AUTO_INCREMENT,
    login VARCHAR(100) NOT NULL,
    clave VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),
    UNIQUE(login)

);


INSERT INTO usuarios (login, clave)
VALUES ('admin', '$2a$10$Dow1dzk3Gz5v9n9JvH3u9e8YxQeZ3Rk8sKXGkF7N8F9Yx7c3A1B2C');