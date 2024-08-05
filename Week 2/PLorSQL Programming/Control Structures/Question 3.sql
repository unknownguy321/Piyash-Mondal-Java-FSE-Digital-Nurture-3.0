Write a PL/SQL block that fetches all loans due in the next 30 days and prints a reminder message for each customer.

DECLARE
    CURSOR loan_cursor IS
        SELECT customer_id, loan_due_date
        FROM loans
        WHERE loan_due_date BETWEEN SYSDATE AND SYSDATE + 30;
        
    v_customer_id loans.customer_id%TYPE;
    v_loan_due_date loans.loan_due_date%TYPE;
BEGIN
    OPEN loan_cursor;
    
    LOOP
        FETCH loan_cursor INTO v_customer_id, v_loan_due_date;
        EXIT WHEN loan_cursor%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan for Customer ID ' || v_customer_id || ' is due on ' || v_loan_due_date);
    END LOOP;
    
    CLOSE loan_cursor;
END;
/
