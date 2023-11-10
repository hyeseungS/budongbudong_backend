DROP TABLE IF EXISTS user;

CREATE TABLE user (
                      user_id bigint not null primary key auto_increment,
                      name varchar(50),
                      email varchar(50) not null unique,
                      password varchar(100),
                      role varchar(100)
)