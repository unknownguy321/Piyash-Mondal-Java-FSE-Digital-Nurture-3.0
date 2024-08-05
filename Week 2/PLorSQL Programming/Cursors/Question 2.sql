Write a PL/SQL block using an explicit cursor ApplyAnnualFee that deducts an annual maintenance fee from the balance of all accounts.

DECLARE
    CURSOR account_cursor IS
        SELECT account_id, balance
        FROM accounts;
        
    v_account_id accounts.account_id%TYPE;
    v_balance accounts.balance%TYPE;
    v_annual_fee CONSTANT NUMBER := 100; -- Annual fee amount
BEGIN
    OPEN account_cursor;
    
    LOOP
        FETCH account_cursor INTO v_account_id, v_balance;
        EXIT WHEN account_cursor%NOTFOUND;
        
        -- Deduct the annual fee from the balance
        UPDATE accounts
        SET balance = balance - v_annual_fee
        WHERE account_id = v_account_id;
    END LOOP;
    
    CLOSE account_cursor;
    COMMIT;
END;
/
