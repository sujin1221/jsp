-- 2023/11/23
CREATE TABLE `board` (
  `bno` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `writer` varchar(100) NOT NULL,
  `cotent` text,
  `regdate` datetime DEFAULT CURRENT_TIMESTAMP,
  `moddate` datetime DEFAULT CURRENT_TIMESTAMP,
  `readcount` int DEFAULT '0',
  PRIMARY KEY (`bno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- 2023/11/27
CREATE TABLE member (
    id VARCHAR(100),
    pwd VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL,
    age INT DEFAULT 0,
    regdate DATETIME DEFAULT CURRENT_TIMESTAMP,
    lastlogin DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
-- 2023/12/4
create table comment (
cno int auto_increment,
bno int not null,
writer varchar(100) default "unknown",
content varchar(1000) not null,
regdate datetime default now(),
primary key(cno));
-- 2023/12/7
alter table board add imageFile varchar(100);