DROP DATABASE IF EXISTS legend;

CREATE DATABASE legend;

USE legend;

CREATE TABLE IF NOT EXISTS user (
	`userNo` INT NOT NULL AUTO_INCREMENT UNIQUE,
    `userId` VARCHAR(16) NOT NULL UNIQUE,
    `userNickname` VARCHAR(16) NOT NULL UNIQUE,
    `userPassword` VARCHAR(200) NOT NULL,
    `userName` VARCHAR(8) NOT NULL,
    `userPhoneNumber` VARCHAR(16) NOT NULL UNIQUE,
    `userEmail` VARCHAR(40) NOT NULL UNIQUE,
    `userGender` VARCHAR(8) NOT NULL,
    `userBirthday` DATE NOT NULL,
    `userRegDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `userExp` INT NOT NULL DEFAULT 0,
    PRIMARY KEY(`userNo`)
    ) engine = InnoDB;

CREATE TABLE IF NOT EXISTS board (
	`boardNo` INT NOT NULL AUTO_INCREMENT UNIQUE,
    `userId` VARCHAR(16),
    `userNickname`  VARCHAR(16),
    `boardClassification` VARCHAR(8) NOT NULL,
	`boardTitle` VARCHAR(40) NOT NULL,
    `boardContent` TEXT NOT NULL,
	`boardRegDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `boardOriginalImg` VARCHAR(40),
    `boardImg` VARCHAR(50),
    `boardViewCount` INT NOT NULL DEFAULT 0,
	`boardFavCount` INT NOT NULL DEFAULT 0,
	`boardModified` BOOLEAN DEFAULT FALSE,
	PRIMARY KEY(`boardNo`),
	FOREIGN KEY (`userId`) REFERENCES user (`userId`) ON DELETE SET NULL
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS favorite (
	`faviriteNo` INT NOT NULL AUTO_INCREMENT UNIQUE,
	`boardNo` INT NOT NULL,
    `userId` VARCHAR(16),
    `userNickname` VARCHAR(16),
	PRIMARY KEY(`faviriteNo`),
    FOREIGN KEY (`userId`) REFERENCES user (`userId`) ON DELETE SET NULL,
	FOREIGN KEY (`boardNo`) REFERENCES board (`boardNo`) ON DELETE CASCADE
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS comment (
	`commentNo` INT NOT NULL AUTO_INCREMENT UNIQUE,
    `boardNo` INT NOT NULL,
	`userId` VARCHAR(16),
    `userNickname` VARCHAR(16),
    `commentContent` TEXT NOT NULL,
    `commentRegDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `commentLikeCount` INT NOT NULL DEFAULT 0,
    `commentModified` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(`commentNo`),
	FOREIGN KEY (`userId`) REFERENCES user (`userId`) ON DELETE SET NULL,
	FOREIGN KEY (`boardNo`) REFERENCES board (`boardNo`) ON DELETE CASCADE
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS friendrequest (
    `friendReqNo` INT NOT NULL AUTO_INCREMENT UNIQUE,
	`userId` VARCHAR(16) NOT NULL,
    `userNickname` VARCHAR(16) NOT NULL,
    `friendId` VARCHAR(16) NOT NULL,
    `friendNickname` VARCHAR(16) NOT NULL,
	PRIMARY KEY(`friendReqNo`),
	FOREIGN KEY (`userId`) REFERENCES user (`userId`) ON DELETE CASCADE,
	FOREIGN KEY (`friendId`) REFERENCES user (`userId`) ON DELETE CASCADE
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS friend (
    `friendNo` INT NOT NULL AUTO_INCREMENT UNIQUE,
	`userId` VARCHAR(16) NOT NULL,
    `userNickname` VARCHAR(16) NOT NULL,
    `friendId` VARCHAR(16) NOT NULL,
    `friendNickname` VARCHAR(16) NOT NULL,
	PRIMARY KEY(`friendNo`),
	FOREIGN KEY (`userId`) REFERENCES user (`userId`) ON DELETE CASCADE,
	FOREIGN KEY (`friendId`) REFERENCES user (`userId`) ON DELETE CASCADE
) engine = InnoDB;


-- INSERT INTO user (userId, userNickname, userPassword, userName, userGender, userEmail, userBirthday, userPhoneNumber)
-- VALUES
-- ('ssafy', '싸피싸피', '$2a$10$Ct9r0MIvZO9TlKEyQEd1vOOxXl08VU.fyft1P48p4GKpaFQq2w2Ny', '김싸피', '남자', 'ssafy@ssafy.com', 20001001, '01011112222'),
-- ('ssafy1', 'ㅇㅇㅇㅇ', '$2a$10$.CyA6w0zQBGTYRyXQXqdLOMy5hW3Hpi06C9EOoiWVYEVajKZy6rOy', '최싸피', '여자', 'ssafy1@ssafy.com', 19251001, '01022221111');

-- INSERT INTO board (userId, userNickname, boardClassification, boardTitle, boardContent)
-- VALUES
-- ('ssafy', '싸피싸피', '공지', '공지사항입니다', '네티켓을 잘 지키세요'),
-- ('ssafy1', 'ㅇㅇㅇㅇ', '공지', '새로운 공지사항입니다', '글을 많이 써주세요');

-- INSERT INTO comment (boardNo, userId, userNickname, commentContent)
-- VALUES
-- (1, 'ssafy1', 'ㅇㅇㅇㅇ', '네~'),
-- (1, 'ssafy', '싸피싸피', '와~~~~'),
-- (2, 'ssafy', '싸피싸피', '좋아요');

SELECT * FROM user;
