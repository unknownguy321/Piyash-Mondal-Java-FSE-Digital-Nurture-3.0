Write a PL/SQL block using an explicit cursor GenerateMonthlyStatements that retrieves all transactions for the current month and prints a statement for each customer.

DECLARE
    CURSOR transaction_cursor IS
        SELECT customer_id, transaction_date, transaction_type, amount
        FROM transactions
        WHERE transaction_date BETWEEN TRUNC(SYSDATE, 'MM') AND LAST_DAY(SYSDATE);
        
    v_customer_id transactions.customer_id%TYPE;
    v_transaction_date transactions.transaction_date%TYPE;
    v_transaction_type transactions.transaction_type%TYPE;
    v_amount transactions.amount%TYPE;
BEGIN
    OPEN transaction_cursor;
    
    LOOP
        FETCH transaction_cursor INTO v_customer_id, v_transaction_date, v_transaction_type, v_amount;
        EXIT WHEN transaction_cursor%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || v_customer_id);
        DBMS_OUTPUT.PUT_LINE('Transaction Date: ' || v_transaction_date);
        DBMS_OUTPUT.PUT_LINE('Transaction Type: ' || v_transaction_type);
        DBMS_OUTPUT.PUT_LINE('Amount: ' || v_amount);
        DBMS_OUTPUT.PUT_LINE('------------------------');
    END LOOP;
    
    CLOSE transaction_cursor;
END;
/
