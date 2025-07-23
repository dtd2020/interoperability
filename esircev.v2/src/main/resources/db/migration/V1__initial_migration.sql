CREATE TABLE addresses
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    street      VARCHAR(255) NOT NULL,
    city        VARCHAR(255) NOT NULL,
    province    VARCHAR(255) NOT NULL,
    zip         VARCHAR(255) NOT NULL,
    user_id     BIGINT       NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE citizens
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    first_name    VARCHAR(255)   NOT NULL,
    last_name     VARCHAR(255)   NOT NULL,
    nuic          VARCHAR(255)   NOT NULL,
    sex           VARCHAR(255)   NOT NULL,
    dob           DATE           NOT NULL,
    bio           LONGTEXT       NULL,
    fathers_name  VARCHAR(255) NOT NULL,
    mothers_name  VARCHAR(255) NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

ALTER TABLE addresses
    ADD CONSTRAINT addresses_users_id_fk FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION;

CREATE INDEX addresses_users_id_fk ON addresses (user_id);