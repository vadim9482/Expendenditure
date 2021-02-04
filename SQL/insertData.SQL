INSERT INTO
    users(
        USER_ID,
        NAME,
        PASSWORD
    )
VALUES
    (1, 'Vadim', 'pass'),
    (2, 'Iryna', 'pass')
;


INSERT INTO
    tables(
        LIST_ID,
        DESCRIPTION,
        USER_ID
    )
VALUES
    (1,'Vadims Plans',1),
    (2,'Iryna Plans',2)
    ;



INSERT INTO 
    payments(
        PAYMENT_ID,
        DESCRIPTION,
        VALUE,
        LIST_ID
    )
VALUES
    (1, 'NOVUS', 125 , 2),
    (2, 'NOVUS', 256 , 2),
    (3, 'Mobile', 200 , 1)
;
