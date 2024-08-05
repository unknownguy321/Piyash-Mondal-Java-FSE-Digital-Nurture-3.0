Write a PL/SQL block that loops through all customers, checks their age, and if they are above 60, apply a 1% discount to their current loan interest rates.

DECLARE
    CURSOR cust_cursor IS
        SELECT customer_id, loan_interest_rate
        FROM customers
        WHERE age > 60;
        
    v_customer_id customers.customer_id%TYPE;
    v_loan_interest_rate customers.loan_interest_rate%TYPE;
BEGIN
    OPEN cust_cursor;
    
    LOOP
        FETCH cust_cursor INTO v_customer_id, v_loan_interest_rate;
        EXIT WHEN cust_cursor%NOTFOUND;
        
        UPDATE customers
        SET loan_interest_rate = loan_interest_rate - (loan_interest_rate * 0.01)
        WHERE customer_id = v_customer_id;
    END LOOP;
    
    CLOSE cust_cursor;
    COMMIT;
END;
/
