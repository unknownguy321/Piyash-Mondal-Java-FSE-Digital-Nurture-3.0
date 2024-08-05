Write a stored procedure TransferFunds that transfers a specified amount from one account to another, checking that the source account has sufficient balance before making the transfer.

CREATE OR REPLACE PROCEDURE TransferFunds(
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
END TransferFunds;
/
