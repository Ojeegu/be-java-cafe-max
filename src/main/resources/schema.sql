CREATE TABLE IF NOT EXISTS USER_TB
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    userId   VARCHAR(50)  NOT NULL,
    password VARCHAR(30)  NOT NULL,
    name     VARCHAR(50)  NOT NULL UNIQUE,
    email    VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS ARTICLE_TB
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    title      VARCHAR(100),
    content    LONGTEXT,
    userName     VARCHAR(50),
    createTime VARCHAR(50),
    FOREIGN KEY(userName) REFERENCES USER_TB(name)
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS COMMENT_TB
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    reId BIGINT,
    articleId BIGINT NOT NULL,
    content LONGTEXT,
    commenter VARCHAR(50),
    createTime DATETIME default now()
);