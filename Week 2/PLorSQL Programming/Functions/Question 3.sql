Write a function HasSufficientBalance that takes an account ID and an amount as input and returns a boolean indicating whether the account has at least the specified amount.

CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account_id NUMBER,
    p_amount NUMBER
) RETURN BOOLEAN IS
    v_balance NUMBER;
BEGIN
    SELECT balance INTO v_balance
    FROM accounts
    WHERE account_id = p_account_id;
    
    IF v_balance >= p_amount THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
END HasSufficientBalance;
/
