CREATE TABLE CUSTOMERS (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(252),
    CREATE_DATE TIMESTAMP,
    PRIMARY KEY (ID)
);

CREATE TABLE ORDERS (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    CUSTOMER_ID BIGINT,
    PRODUCT_NAME VARCHAR(128),
    PRICE BIGINT,
    PRIMARY KEY (ID),
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS(ID)
);

CREATE TABLE CAR (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    MODEL_NAME VARCHAR(252),
    MODEL_YEAR BIGINT,
    CREATE_DATE TIMESTAMP,
    PRIMARY KEY (ID)
);

CREATE TABLE TODOS (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    TITLE VARCHAR(128),
    DESCRIPTION VARCHAR(128),
    DELETED NUMBER(1),
    PRIMARY KEY (ID)
);

CREATE TABLE PERSONS (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    FIRSTNAME VARCHAR(128),
    LASTNAME VARCHAR(128),
    AGE NUMBER(11),
    DELETED NUMBER(1),
    PRIMARY KEY (ID)
);

CREATE TABLE NOTIFICATION (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    FIRST_NAME VARCHAR(25),
    LAST_NAME VARCHAR(25),
    CONTENT VARCHAR(25),
    PRIMARY KEY (ID)
);

CREATE TABLE SMS_NOTIFICATION (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    PHONE_NUMBER VARCHAR(25),
    PRIMARY KEY (ID)
);

CREATE TABLE EMAIL_NOTIFICATION (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    EMAIL VARCHAR(100),
    PRIMARY KEY (ID)
);

CREATE TABLE EMPLOYEE (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    FIRST_NAME VARCHAR(25),
    LAST_NAME VARCHAR(25),
    HOURLY_RATE BIGINT,
    SALARY BIGINT,
    DTYPE VARCHAR(25),
    PRIMARY KEY (ID)
);

CREATE TABLE GAME (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(25),
    DESCRIPTION VARCHAR(255),
    ROUND_NO BIGINT,
    SESSION_ID BIGINT,
    LEAGUE_ID BIGINT,
    POSTPONED NUMBER(1),
    PLAYED NUMBER(1),
    CREATE_DATE TIMESTAMP,
    PRIMARY KEY (ID)
);

CREATE TABLE PAYMENT (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    AMOUNT BIGINT,
    CARD_NO BIGINT,
    CHEQUE_NO BIGINT,
    PRIMARY KEY (ID)
);

CREATE TABLE CARD_PAYMENT (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    AMOUNT BIGINT,
    CARD_NO BIGINT,
    CHEQUE_NO BIGINT,
    PRIMARY KEY (ID)
);

CREATE TABLE CHEQUE_PAYMENT (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    AMOUNT BIGINT,
    CARD_NO BIGINT,
    CHEQUE_NO BIGINT,
    PRIMARY KEY (ID)
);

CREATE TABLE NEWS (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    TITLE VARCHAR(128),
    CATEGORY_NAME VARCHAR(128),
    DESCRIPTION VARCHAR(128),
    DELETED NUMBER(1) DEFAULT 0,
    PRIMARY KEY (ID)
);

CREATE TABLE POST (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    TITLE VARCHAR(128),
    DESCRIPTION VARCHAR(128),
    PRIMARY KEY (ID)
);

CREATE TABLE TAGS (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(128),
    PRIMARY KEY (ID)
);

CREATE TABLE POST_TAGS (
    POST_ID BIGINT NOT NULL,
    TAG_ID BIGINT NOT NULL,
    PRIMARY KEY (POST_ID,TAG_ID)
);
