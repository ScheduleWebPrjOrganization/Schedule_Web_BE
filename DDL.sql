DROP TABLE `subject_record`;

CREATE TABLE `subject_record`
(
    `recorded_at`    DATE NOT NULL COMMENT '기록 날짜',
    `stopped_at`    DATE NOT NULL COMMENT '기록 시간',
    `subject_id`    BIGINT NOT NULL COMMENT '기록 id',
    `id`    BIGINT NOT NULL COMMENT '과목 id',
    PRIMARY KEY ( `subject_id` )
) COMMENT = '수행 시간 기록';

ALTER TABLE `subject_record`
    ADD CONSTRAINT `subject_record_PK` PRIMARY KEY ( `subject_id` );


DROP TABLE `report`;

CREATE TABLE `report`
(
    `id`    BIGINT NOT NULL COMMENT '신고 id',
    `챗 id`    BIGINT NOT NULL COMMENT '챗 id',
    `회원 id`    VARCHAR(30) NOT NULL COMMENT '회원 id',
    PRIMARY KEY ( `id` )
) COMMENT = '신고';

ALTER TABLE `report`
    ADD CONSTRAINT `report_PK` PRIMARY KEY ( `id` );


DROP TABLE `task`;

CREATE TABLE `task`
(
    `id`    BIGINT NOT NULL COMMENT '작업 id',
    `name`    VARCHAR(30) NOT NULL COMMENT '작업명',
    `status`    VARCHAR(30) NOT NULL COMMENT '달성 상태',
    `member_id`    VARCHAR(30) COMMENT '회원 id',
    `subject_id`    BIGINT COMMENT '과목 id',
    PRIMARY KEY ( `id` )
) COMMENT = '작업';

ALTER TABLE `task`
    ADD CONSTRAINT `task_PK` PRIMARY KEY ( `id` );


DROP TABLE `memeber`;

CREATE TABLE `memeber`
(
    `id`    VARCHAR(30) NOT NULL COMMENT '회원 id',
    `pwd`    VARCHAR(30) NOT NULL COMMENT '비밀번호',
    `email`    VARCHAR(30) NOT NULL COMMENT '이메일',
    `created_at`    DATE NOT NULL COMMENT '회원 생성 날짜',
    `user_type`    VARCHAR(30) NOT NULL COMMENT '회원 등급',
    `group_id`    INTEGER NOT NULL COMMENT '그룹 id',
    `level`    VARCHAR(30) NOT NULL COMMENT '성실함 레벨',
    PRIMARY KEY ( `id` )
) COMMENT = '회원';

ALTER TABLE `memeber`
    ADD CONSTRAINT `memeber_PK` PRIMARY KEY ( `id` );


DROP TABLE `group_chat`;

CREATE TABLE `group_chat`
(
    `content`    VARCHAR(255) NOT NULL COMMENT '채팅 내용',
    `sent_at`    DATE NOT NULL COMMENT '채팅 발송 시간',
    `member_id`    VARCHAR(30) NOT NULL COMMENT '회원 id',
    `group_id`    INTEGER NOT NULL COMMENT '그룹 id',
    `id`    BIGINT NOT NULL COMMENT '챗 id',
    PRIMARY KEY ( `id` )
) COMMENT = '그룹 채팅';

ALTER TABLE `group_chat`
    ADD CONSTRAINT `group_chat_PK` PRIMARY KEY ( `id` );


DROP TABLE `study_group`;

CREATE TABLE `study_group`
(
    `id`    INTEGER NOT NULL COMMENT '그룹 id',
    `member_count`    INTEGER NOT NULL COMMENT '인원수',
    `description`    VARCHAR(255) NOT NULL COMMENT '그룹 설명',
    `name`    VARCHAR(30) NOT NULL COMMENT '그룹 이름',
    PRIMARY KEY ( `id` )
) COMMENT = '스터디 그룹';

ALTER TABLE `study_group`
    ADD CONSTRAINT `study_group_PK` PRIMARY KEY ( `id` );


DROP TABLE `subject`;

CREATE TABLE `subject`
(
    `id`    BIGINT NOT NULL COMMENT '과목 id',
    `member_id`    VARCHAR(30) COMMENT '회원 id',
    `name`    VARCHAR(30) NOT NULL COMMENT '기록 과목 이름',
    PRIMARY KEY ( `id` )
) COMMENT = '과목';

ALTER TABLE `subject`
    ADD CONSTRAINT `subject_PK` PRIMARY KEY ( `id` );


DROP TABLE `statistic`;

CREATE TABLE `statistic`
(
    `total_record`    INTEGER NOT NULL COMMENT '누적 공부 시간',
    `subject_id`    BIGINT COMMENT '과목 id',
    `memeber_id`    VARCHAR(30) COMMENT '회원 id',
    `id1`    BIGINT NOT NULL COMMENT '통계 id',
    PRIMARY KEY ( `id1` )
) COMMENT = '통계';

ALTER TABLE `statistic`
    ADD CONSTRAINT `statistic_PK` PRIMARY KEY ( `id1` );


