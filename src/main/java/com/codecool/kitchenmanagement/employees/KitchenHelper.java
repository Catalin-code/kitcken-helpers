package com.codecool.kitchenmanagement.employees;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import com.codecool.kitchenmanagement.Ingredient;

/**
 * A kitchen helper is an employee with a stock of ingredients.
 *
 * At any given moment a helper has [0-3] of any ingredient.
 */
public class KitchenHelper extends Employee {
    private static final int MAX_NUMBER_OF_INGREDIENTS = 3;
    private final Random random = new Random(); // per-instance random state for refill
    private final Map<Ingredient, Integer> ingredients = new HashMap<>();

    /**
     * Creates a kitchen helper with no ingredients of any kind.
     */
    public KitchenHelper(String name, LocalDate birthDate, int salary) {
        super(name, birthDate, salary);
        for (var ingredient: Ingredient.values()) {
            ingredients.put(ingredient, 0);
        }
    }

    /**
     * Refills the ingredients held by the kitchen helper.
     * <p>
     * Each kind of ingredient is refilled to a maximum of 3 and a
     * minimum of the former value.
     */
    public void refillIngredients() {
        for (var ingredient: Ingredient.values()) {
            var amount = random.nextInt(MAX_NUMBER_OF_INGREDIENTS+1);
            ingredients.merge(ingredient, amount, Math::max);
        }
    }

    /**
     * Give up an ingredient if the helper possesses it.
     *
     * @param the type of the requested ingredient.
     *
     * @return an optional wrapped ingredient if found.  An empty
     *         optional otherwise.
     *
     * @throws IllegalStateException when the ingredient stocks have
     *         incosistencies. (unlikely)
     */
    public Optional<Ingredient> giveUpIngredient(Ingredient ingredient) {
        if (hasIngredient(ingredient)) {
            decreaseAmountOf(ingredient);
            return Optional.of(ingredient);
        }

        return Optional.empty();
    }

    /**
     * Yells about running out of ingredients.
     */
    public void yell() {
        System.out.println("We're all out!");
    }

    private boolean hasIngredient(Ingredient ingredient) {
        return ingredients.get(ingredient) > 0;
    }

    private void decreaseAmountOf(Ingredient ingredient) {
        var amount = ingredients.get(ingredient);
        if (amount == 0) {
            throw new IllegalStateException("Tried to decrease empty ingredient stock.");
        }

        ingredients.replace(ingredient, amount-1);
    }
}