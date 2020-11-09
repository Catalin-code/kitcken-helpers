package com.codecool.kitchenmanagement.employees;

import java.time.LocalDate;

/**
 * A cooker employee.  Kitchens employ multiple cooks.
 */
public class Cook extends CookerEmployee {
    /**
     * {@inheritDoc}
     */
    public Cook(String name, LocalDate birthDate, int salary) {
        super(name, birthDate, salary);
    }

    /**
     * perform the cooking duties of a cook.
     */
    public void cookWithKnives() {
        System.out.println("I'm cooking");
        // might expand on it in later sprints
    }
}