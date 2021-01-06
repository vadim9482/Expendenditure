CREATE USER
    expendenditureAdmin
WITH
    CREATEDB PASSWORD
    'admin';
SET ROLE
    expendenditureAdmin;
CREATE DATABASE
       transactions
OWNER
     expendenditureAdmin;
