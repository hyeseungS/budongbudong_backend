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

DROP TABLE IF EXISTS dong_code;
CREATE TABLE dong_code (
                        dong_code VARCHAR(10) NOT NULL PRIMARY KEY,
                        sido_name VARCHAR(30),
                        gugun_name VARCHAR(30),
                        dong_name VARCHAR(30)
);

DROP TABLE IF EXISTS apt;
CREATE TABLE apt (
                     apt_id VARCHAR(10) NOT NULL PRIMARY KEY,
                     dong_code VARCHAR(10),
                     apt_name VARCHAR(225),
                     total_dong VARCHAR(255),
                     sale_price VARCHAR(255),
                     rent_price VARCHAR(255),
                     total_households VARCHAR(225),
                     apt_floor VARCHAR(30),
                     approval_date VARCHAR(30),
                     total_parking_lot VARCHAR(225),
                     floor_area_ratio VARCHAR(225),
                     building_land_ratio VARCHAR(225),
                     construction_company VARCHAR(225),
                     heating_system VARCHAR(225),
                     management_office VARCHAR(30),
                     apt_address VARCHAR(225),
                     lat VARCHAR(225),
                     lng VARCHAR(225),
                     school_name VARCHAR(225),
                     school_dist VARCHAR(225),
                     school_address VARCHAR(225),
                     apt_hit BIGINT DEFAULT 0,
                     total_score BIGINT DEFAULT 0,
                     review_score BIGINT DEFAULT 0,
                     facilities_score BIGINT DEFAULT 0,
                     store_score BIGINT DEFAULT 0,
                     food_score BIGINT DEFAULT 0,
                     medical_score BIGINT DEFAULT 0,
                     education_score BIGINT DEFAULT 0,
                     FOREIGN KEY (dong_code) REFERENCES dong_code(dong_code)
);

DROP TABLE IF EXISTS apt_area;
CREATE TABLE apt_area (
                          apt_area_id INT NOT NULL,
                          apt_area VARCHAR(10) NOT NULL,
                          apt_id VARCHAR(10) NOT NULL,
                          apt_area_img VARCHAR(225),
                          apt_area_supply_exclusive VARCHAR(225),
                          total_room VARCHAR(30),
                          apt_area_total_households VARCHAR(30),
                          front_door_structure VARCHAR(30),
                          price VARCHAR(225),
                          apt_area_real_trans VARCHAR(225),
                          administration_cost VARCHAR(225),
                          holding_tax VARCHAR(225),
                          upper_limit_sale VARCHAR(30),
                          lower_limit_sale VARCHAR(30),
                          upper_limit_rent VARCHAR(30),
                          lower_limit_rent VARCHAR(30),
                          sale_comp_rent VARCHAR(30),
                          FOREIGN KEY (apt_id) REFERENCES apt(apt_id),
                          PRIMARY KEY (apt_area_id, apt_area, apt_id)
);

DROP TABLE IF EXISTS apt_real_history;
CREATE TABLE apt_real_history (
                                  apt_real_history_id BIGINT NOT NULL PRIMARY KEY auto_increment,
                                  apt_area_id INT NOT NULL,
                                  apt_area VARCHAR(10) NOT NULL,
                                  apt_id VARCHAR(10) NOT NULL,
                                  apt_real_history_month VARCHAR(30),
                                  apt_real_history_type VARCHAR(30),
                                  apt_real_history_price VARCHAR(255),
                                  FOREIGN KEY (apt_area_id, apt_area, apt_id) REFERENCES apt_area(apt_area_id, apt_area, apt_id)
);

DROP TABLE IF EXISTS apt_real_trans;
CREATE TABLE apt_real_trans (
                                apt_real_trans_id BIGINT NOT NULL PRIMARY KEY auto_increment,
                                apt_id VARCHAR(10) NOT NULL,
                                apt_real_trans_name VARCHAR(225),
                                apt_real_trans_price VARCHAR(30),
                                apt_real_trans_img VARCHAR(225),
                                apt_real_trans_floor VARCHAR(30),
                                apt_real_trans_dir VARCHAR(30),
                                apt_real_trans_area VARCHAR(225),
                                FOREIGN KEY (apt_id) REFERENCES apt(apt_id)
);

DROP TABLE IF EXISTS scrap;
CREATE TABLE scrap (
                       scrap_id BIGINT NOT NULL PRIMARY KEY auto_increment,
                       apt_real_trans_id BIGINT NOT NULL,
                       user_id BIGINT NOT NULL,
                       create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (apt_real_trans_id) REFERENCES apt_real_trans(apt_real_trans_id),
                       FOREIGN KEY (user_id) REFERENCES user(user_id)
);

DROP TABLE IF EXISTS review;
CREATE TABLE review (
                       review_id BIGINT NOT NULL PRIMARY KEY auto_increment,
                       review_comment VARCHAR(225),
                       review_score INT,
                       apt_id VARCHAR(10) NOT NULL,
                       user_id BIGINT NOT NULL,
                       create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (apt_id) REFERENCES apt(apt_id),
                       FOREIGN KEY (user_id) REFERENCES user(user_id)
);

DROP TABLE IF EXISTS qa;
CREATE TABLE qa (
                        qa_id BIGINT NOT NULL PRIMARY KEY auto_increment,
                        qa_title VARCHAR(225),
                        qa_content VARCHAR(225),
                        qa_comment VARCHAR(255),
                        comment_status BOOLEAN,
                        user_id BIGINT NOT NULL,
                        create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE category_info (
                               category_code VARCHAR(3) PRIMARY KEY,
                               category_id VARCHAR(2),
                               category_name VARCHAR(255),
                               sub_category_name VARCHAR(255)
);

INSERT INTO category_info (category_code, category_id, category_name, sub_category_name)
VALUES  ('10', 'G0', '종합소매', NULL),
        ('101', 'G1', '종합소매', '슈퍼'),
        ('102', 'G2', '종합소매', '편의점'),
        ('103', 'G3', '종합소매', '기타'),
        ('20', 'F0', '음식', NULL),
        ('201', 'F1', '음식', '한식'),
        ('202', 'F2', '음식', '중식'),
        ('203', 'F3', '음식', '일식'),
        ('204', 'F4', '음식', '서양식'),
        ('205', 'F5', '음식', '동남아'),
        ('206', 'F6', '음식', '카페'),
        ('207', 'F7', '음식', '패스트푸드'),
        ('208', 'F8', '음식', '기타&주점'),
        ('30', 'P0', '교육', NULL),
        ('301', 'P1', '교육', '일반교육'),
        ('302', 'P2', '교육', '기타교육'),
        ('303', 'P3', '교육', '편의(도서관&독서실)'),
        ('40', 'Q0', '보건의료', NULL),
        ('401', 'Q1', '보건의료', '병원'),
        ('402', 'Q2', '보건의료', '의원'),
        ('50', 'R0', '편의', NULL),
        ('501', 'R1', '편의', '세탁'),
        ('502', 'R2', '편의', '오락');


CREATE TABLE store_info (
                            store_id BIGINT PRIMARY KEY,
                            store_name VARCHAR(255),
                            category_code VARCHAR(3),
                            dong_code VARCHAR(10),
                            street_address VARCHAR(255),
                            lng VARCHAR(225),
                            lat VARCHAR(225),
                            FOREIGN KEY (category_code) REFERENCES category_info(category_code)
);

