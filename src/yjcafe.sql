drop database yjcafe;
create database yjcafe;
use yjcafe;

CREATE TABLE 회원 (
  u_number INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  u_name VARCHAR(12) NOT NULL,
  u_id VARCHAR(16) NOT NULL UNIQUE,
  u_password VARCHAR(30) NOT NULL,
  u_phone_number VARCHAR(11) NOT NULL UNIQUE,
  u_birthday DATE,
  u_email VARCHAR(25) NOT NULL UNIQUE,
  u_cash INT NOT NULL DEFAULT 0,
  u_enroll_date DATE NOT NULL,
  u_profile_image VARCHAR(100),
  u_department VARCHAR(100)
);

CREATE TABLE 카페 (
  k_number INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  k_name VARCHAR(20) NOT NULL UNIQUE,
  k_description VARCHAR(100),
  k_open_time TIME NOT NULL DEFAULT '08:00:00',
  k_close_time TIME NOT NULL DEFAULT '22:00:00',
  k_address VARCHAR(100) NOT NULL,
  k_location VARCHAR(80) NOT NULL,
  k_image VARCHAR(100)
);

CREATE TABLE 메뉴 (
  m_number INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  m_name VARCHAR(50) NOT NULL,
  m_description VARCHAR(100) NOT NULL,
  m_price INT NOT NULL,
  m_total_price INT NOT NULL,
  k_number INT NOT NULL,
  FOREIGN KEY (k_number) REFERENCES 카페(k_number)
    ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE 메뉴_이미지 (
  m_image VARCHAR(100),
  m_number INT NOT NULL,
  FOREIGN KEY (m_number) REFERENCES 메뉴(m_number)
    ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE 예약 (
  b_number INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  b_guest_quantity INT NOT NULL DEFAULT 1,
  b_payment_method VARCHAR(16) NOT NULL DEFAULT '보유 포인트',
  b_payment_cost INT NOT NULL DEFAULT 0,
  b_status VARCHAR(10) NOT NULL DEFAULT '기록',
  b_payment_date DATE NOT NULL,
  u_number INT NOT NULL,
  k_number INT NOT NULL,
  m_number INT NOT NULL,
  FOREIGN KEY (u_number) REFERENCES 회원(u_number)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (k_number) REFERENCES 카페(k_number)
    ON DELETE NO ACTION ON UPDATE CASCADE,
  FOREIGN KEY (m_number) REFERENCES 메뉴(m_number)
    ON DELETE NO ACTION ON UPDATE CASCADE,
  CHECK (b_status IN ('예약중', '기록', '취소')),
  CHECK (b_payment_method = '보유 포인트')
);

CREATE TABLE 예약_일자 (
  b_date DATE NOT NULL,
   b_hour int not null,
  b_minute int not null,
  b_number INT NOT NULL,
  FOREIGN KEY (b_number) REFERENCES 예약(b_number)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE 쿠폰 (
  c_number INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  c_serial_number CHAR(16) NOT NULL UNIQUE,
  c_name VARCHAR(30) NOT NULL,
  c_display VARCHAR(10) NOT NULL DEFAULT '출력',
  c_rate DOUBLE NOT NULL,
  c_min_date DATE NOT NULL,
  c_max_date DATE NOT NULL,
  c_min_age INT,
  c_max_age INT,
  c_min_cost INT,
  c_max_enroll_date DATE,
  c_min_enroll_date DATE,
  CHECK (LENGTH(c_serial_number) = 16),
  CHECK (c_display IN ('출력', '숨김')),
  CHECK (c_min_date <= c_max_date)
);

CREATE TABLE 쿠폰_보유상태 (
  c_status VARCHAR(16) NOT NULL DEFAULT '보유중',
  c_number INT NOT NULL,
  u_number INT NOT NULL,
  FOREIGN KEY (c_number) REFERENCES 쿠폰(c_number)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (u_number) REFERENCES 회원(u_number)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CHECK (c_status IN ('보유중', '사용완료'))
);

CREATE TABLE 공지사항 (
  n_number INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  n_title VARCHAR(50) NOT NULL,
  n_content VARCHAR(500) NOT NULL,
  n_date DATE NOT NULL
);

CREATE TABLE 공지사항_이미지 (
  n_image VARCHAR(100) NOT NULL,
  n_number INT NOT NULL,
  FOREIGN KEY (n_number) REFERENCES 공지사항(n_number)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE 예약_상세 (
  b_detail_number INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  b_number INT NOT NULL,
  m_number INT NOT NULL,
  quantity INT NOT NULL DEFAULT 1,
  FOREIGN KEY (b_number) REFERENCES 예약(b_number)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (m_number) REFERENCES 메뉴(m_number)
    ON DELETE NO ACTION ON UPDATE CASCADE
);
