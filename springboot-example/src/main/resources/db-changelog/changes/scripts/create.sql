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

CREATE TABLE TODOS (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(128),
    description VARCHAR(128),
    deleted NUMBER(1),
    PRIMARY KEY (ID)
);
