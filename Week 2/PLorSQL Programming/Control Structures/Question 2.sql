Write a PL/SQL block that iterates through all customers and sets a flag IsVIP to TRUE for those with a balance over $10,000.

DECLARE
    CURSOR cust_cursor IS
        SELECT customer_id, balance
        FROM customers
        WHERE balance > 10000;
        
    v_customer_id customers.customer_id%TYPE;
BEGIN
    OPEN cust_cursor;
    
    LOOP
        FETCH cust_cursor INTO v_customer_id;
        EXIT WHEN cust_cursor%NOTFOUND;
        
        UPDATE customers
        SET IsVIP = TRUE
        WHERE customer_id = v_customer_id;
    END LOOP;
    
    CLOSE cust_cursor;
    COMMIT;
END;
/
