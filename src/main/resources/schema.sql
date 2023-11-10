DROP TABLE IF EXISTS user;

CREATE TABLE user (
                      user_id BIGINT NOT NULL PRIMARY KEY auto_increment,
                      name VARCHAR(50),
                      email VARCHAR(50) NOT NULL UNIQUE,
                      password VARCHAR(100),
                      role VARCHAR(100),
                      create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS notice;
CREATE TABLE notice (
                        notice_id BIGINT NOT NULL PRIMARY KEY auto_increment,
                        user_id BIGINT,
                        notice_title VARCHAR(50) NOT NULL,
                        notice_content VARCHAR(255) NOT NULL,
                        create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (user_id) REFERENCES user(user_id)
);