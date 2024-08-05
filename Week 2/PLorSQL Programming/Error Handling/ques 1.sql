Write a stored procedure SafeTransferFunds that transfers funds between two accounts. 
Ensure that if any error occurs (e.g., insufficient funds), an appropriate error message is logged and the transaction is rolled back.

CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_from_account_id IN NUMBER,
    p_to_account_id IN NUMBER,
    p_amount IN NUMBER
) IS
    insufficient_funds EXCEPTION;
    v_balance NUMBER;
BEGIN
    -- Check if the from account has sufficient funds
    SELECT balance INTO v_balance
    FROM accounts
    WHERE account_id = p_from_account_id;
    
    IF v_balance < p_amount THEN
        RAISE insufficient_funds;
    END IF;
    
    -- Perform the fund transfer
    UPDATE accounts
    SET balance = balance - p_amount
    WHERE account_id = p_from_account_id;
    
    UPDATE accounts
    SET balance = balance + p_amount
    WHERE account_id = p_to_account_id;
    
    COMMIT;
    
EXCEPTION
    WHEN insufficient_funds THEN
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in account ' || p_from_account_id);
        ROLLBACK;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        ROLLBACK;
END SafeTransferFunds;
/
