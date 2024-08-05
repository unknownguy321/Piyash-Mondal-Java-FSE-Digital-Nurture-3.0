Write a function CalculateAge that takes a customer's date of birth as input and returns their age in years.

CREATE OR REPLACE FUNCTION CalculateAge(p_dob DATE) RETURN NUMBER IS
    v_age NUMBER;
BEGIN
    SELECT TRUNC((SYSDATE - p_dob) / 365.25) INTO v_age FROM dual;
    RETURN v_age;
END CalculateAge;
/
