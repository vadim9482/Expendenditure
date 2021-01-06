use db_name

source /home/vadim9482/workspace/Expendenditure/SQL/createRoleAndDatabase.SQL

CREATE USER 'expendenditureAdmin'@'localhost' IDENTIFIED BY 'Witcher_9482';


CREATE TABLE 

        USER (USER_ID int, NAME VARCHAR(128));


\dt
\q
\


CREATE TABLE
    PAYMENTS(
        ID          VARCHAR(128) PRIMARY KEY, 
        NAME        VARCHAR(128) NOT NULL,
        PAYMENT_VALUE       INT
    );

CREATE USER
    expendenditureAdmin
WITH
    CREATEDB PASSWORD
    'user';  
