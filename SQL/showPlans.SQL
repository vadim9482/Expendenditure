SELECT 
    PAYMENT_ID AS "ID",
    DESCRIPTION as "Description",
    VALUE AS "Value"
FROM 
    plans
    INNER JOIN
        users
        USING (USER_ID)
WHERE
    USER_ID='1';
