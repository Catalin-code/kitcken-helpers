package com.codecool.kitchenmanagement.employees;

import java.time.LocalDate;

/**
 * Abstract class representing employees who can cook and possibly
 * possess a knife set.
 */
public abstract class CookerEmployee extends Employee {
    private boolean hasKnife; // initialized to false

    /**
     * {@inheritDoc}
     */
    public CookerEmployee(String name, LocalDate birthDate, int salary) {
        super(name, birthDate, salary); // call the constructor of Employee
    }

    /**
     * Give a knife set to the employee
     * <p>
     * An employee without a knife set cannot cook.
     */
    public void receiveKnife() {
        hasKnife = true;
    }

    /**
     * Checks whether the employee has already received their knife
     * set.
     *
     * @return true if the employee has already received their knife
     *         set.  Return false otherwise.
     */
    public boolean hasKnife() {
        return hasKnife;
    }

    /**
     * Performs the cooking process of the employee.
     *
     * Override the {@code cookWithKnives} method to implement
     * specific cooking logic in child classes.
     *
     * @throws IllegalStateException if the employee hasn't received
     *         their knife set yet.
     */
    public final void cook() {
        if (!hasKnife) {
            var formatString = // extract to static final?
                    "Can't cook: The employee %s hasn't received their knife set yet.";
            var message = String.format(formatString, toString());
            throw new IllegalStateException(message);
        }

        cookWithKnives();
    }

    /**
     * Perform class-specific cooking logic.
     */
    protected abstract void cookWithKnives();
}