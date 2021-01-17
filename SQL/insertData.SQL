INSERT INTO
    users(
        USER_ID,
        NAME,
        PASSWORD
    )
VALUES
    (1, 'Vadim', 'pass'),
    (2, 'Iryna', 'pass');

INSERT INTO 
    plans(
        PAYMENT_ID,
        DESCRIPTION,
        VALUE,
        USER_ID
    )
VALUES
    (1, 'NOVUS', 125 , 2),
    (2, 'NOVUS', 256 , 2),
    (3, 'Mobile', 200 , 1);
