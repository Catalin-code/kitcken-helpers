package com.codecool.kitchenmanagement;

import java.util.HashSet;
import java.util.Set;
import java.util.Optional;
import java.util.stream.Stream;

import com.codecool.kitchenmanagement.employees.Chef;
import com.codecool.kitchenmanagement.employees.CookerEmployee;
import com.codecool.kitchenmanagement.employees.Employee;
import com.codecool.kitchenmanagement.employees.KitchenHelper;

/**
 * A kitchen where employees cook meals.
 */
public class Kitchen implements IngredientStore {
    private final Set<Employee> employees = new HashSet<>();
    private Chef chef; // starts out as `null`
    private final Set<CookerEmployee> cookers = new HashSet<>();
    private final Set<KitchenHelper> helpers = new HashSet<>();

    /**
     * Hire an employee
     * <p>
     * If the hired employee is a chef then release the former chef if
     * any.
     *
     * @param employee the employee to hire
     */
    public void hireEmployee(Employee employee) {
        employees.add(employee);

        if (employee instanceof Chef) {
            hireChef((Chef) employee);
        }

        if (employee instanceof CookerEmployee) {
            cookers.add((CookerEmployee) employee);
        }

        if (employee instanceof KitchenHelper) {
            KitchenHelper newHelper = (KitchenHelper) employee;
            helpers.add(newHelper);
        }
    }

    /**
     * Conduct a shift making meals.
     *
     * @throws IllegalStateException if there is no chef hired.
     */
    public void conductAShift() {
        if (chef == null) {
            throw new IllegalStateException("Can't start a shift without a chef.");
        }

        helpers.forEach(KitchenHelper::refillIngredients);

        for (var cooker : cookers) {
            try {
                cooker.cook();
            } catch (IllegalStateException e) {
                // report error but otherwise go on
                System.out.println("Error " + e.getMessage());
            }
        }
    }

    /**
     * Iterate through the kitchen helpers for the requested ingredient.
     * If the ingredient presents then returns with it.
     * In case of missing ingredient invokes kitchen helpers' yell method and retuns with empty.
     *
     * @param ingredient demanded ingredient
     */
    @Override
    public Optional<Ingredient> requestIngredient(Ingredient ingredient) {
        for (KitchenHelper helper : helpers) {
            Optional<Ingredient> received = helper.giveUpIngredient(ingredient);
            if (!received.isEmpty()){
                return received;
            }
        }
        helpers.forEach(KitchenHelper::yell);
        return Optional.empty();
    }

    private void hireChef(Chef newChef) {
        if (this.chef != null) {
            fireChef();
        }
        this.chef = newChef;
        this.chef.setStore(this);
    }

    private void fireChef() {
        chef.setStore(null);
        employees.remove(chef);
    }
}