Write a stored procedure UpdateSalary that increases the salary of an employee by a given percentage. If the employee ID does not exist, handle the exception and log an error message.

CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_employee_id IN NUMBER,
    p_percentage IN NUMBER
) IS
    employee_not_found EXCEPTION;
    v_salary NUMBER;
BEGIN
    -- Check if the employee exists
    SELECT salary INTO v_salary
    FROM employees
    WHERE employee_id = p_employee_id;
    
    -- If no rows found, raise an exception
    IF SQL%NOTFOUND THEN
        RAISE employee_not_found;
    END IF;
    
    -- Update the employee's salary
    UPDATE employees
    SET salary = salary + (salary * p_percentage / 100)
    WHERE employee_id = p_employee_id;
    
    COMMIT;
    
EXCEPTION
    WHEN employee_not_found THEN
        DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_employee_id || ' not found.');
        ROLLBACK;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        ROLLBACK;
END UpdateSalary;
/
