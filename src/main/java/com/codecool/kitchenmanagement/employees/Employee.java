package com.codecool.kitchenmanagement.employees;

import java.time.LocalDate;

/**
 * Abstract class representing a kitchen employee.
 */
public abstract class Employee {
    private String name;
    private LocalDate birthDate;
    private int salary;

    /**
     * Creates an employee instance
     *
     * @param name the full name of the employee
     * @param birthDate the employee's year of birth
     * @param salary the monthly salary of the employee
     */
    public Employee(String name, LocalDate birthDate, int salary) {
        this.name = name;
        this.birthDate = birthDate;
        this.salary = salary;
    }

    /**
     * Get the full name of the employee
     *
     * @return the full name of the employee as a single string
     */
    public String getName() {
        return name;
    }

    /**
     * Get the date of birth of the employee
     *
     * @return the date of birth of the employee as a LocalDate
     */
    public LocalDate getbirthDate() {
        return birthDate;
    }

    /**
     * Get the monthly salary of the employee.
     *
     * @return the monthly salary of the employee.
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Set the monthly salary of the employee.
     *
     * @param salary the new monthly salary of the employee.
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * Prints the employee's tax to the console.
     */
    public void printTax() {
        int tax = (int) (salary * 0.99);
        System.out.format("My tax is %d.%s", tax, System.lineSeparator());
    }

    /**
     * String representation of the employee.
     * <p>
     * Prints the name and birth year of the employee.
     *
     * @return the employee represented as a printable string
     */
    @Override
    public String toString() {
        return String.format("Employee { name = \"%s\", year of birth = %d }",
                name, birthDate.getYear());
    }
}