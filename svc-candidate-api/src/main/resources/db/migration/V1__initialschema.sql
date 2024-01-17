CREATE TABLE tb_user
(
    id         BIGINT       NOT NULL,
    email      VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    country    VARCHAR(255) NOT NULL,
    city       VARCHAR(255) NOT NULL,
    zip        VARCHAR(11)  NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT UC_Email UNIQUE (email)
);