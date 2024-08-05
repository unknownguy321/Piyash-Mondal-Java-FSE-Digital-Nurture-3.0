Write a trigger CheckTransactionRules that ensures withdrawals do not exceed the balance and deposits are positive before inserting a record into the Transactions table.

CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    IF :NEW.transaction_type = 'WITHDRAWAL' THEN
        -- Check if the account has sufficient balance for the withdrawal
        SELECT balance INTO v_balance
        FROM accounts
        WHERE account_id = :NEW.account_id;
        
        IF v_balance < :NEW.amount THEN
            RAISE_APPLICATION_ERROR(-20001, 'Error: Insufficient balance for withdrawal.');
        END IF;
    ELSIF :NEW.transaction_type = 'DEPOSIT' THEN
        -- Check if the deposit amount is positive
        IF :NEW.amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Error: Deposit amount must be positive.');
        END IF;
    END IF;
END CheckTransactionRules;
/
