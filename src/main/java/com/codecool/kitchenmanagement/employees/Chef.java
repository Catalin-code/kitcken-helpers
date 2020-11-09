package com.codecool.kitchenmanagement.employees;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

import com.codecool.kitchenmanagement.Ingredient;
import com.codecool.kitchenmanagement.IngredientStore;

public class Chef extends CookerEmployee {
    // instance-wise random for requests
    private final Random random = new Random();
    // initialized to `null`
    private IngredientStore store;

    /**
     * {@inheritDoc}
     */
    public Chef(String name, LocalDate birthDate, int salary) {
        super(name, birthDate, salary);
    }

    /**
     * Set the ingredient store of the chef.
     *
     * @param store the new ingredient store of the chef.
     */
    public void setStore(IngredientStore store) {
        this.store = store;
    }

    /**
     * In some cases the chef asks for an ingredient from the kitchen
     * helpers.
     * <p>
     * Requests and their results are logged.
     */
    @Override
    protected void cookWithKnives() {
        // not specified when, so going 50-50
        var shouldAskForIngredient = random.nextBoolean();

        if (!shouldAskForIngredient || store == null) {
            return;
        }

        var randomIngredient = Ingredient.randomIngredient();
        System.out.format("I need %s\n", randomIngredient);
        Optional<Ingredient> received = store.requestIngredient(randomIngredient);
        String ingredientName = received.map(Ingredient::toString).orElse("NOTHING");
        System.out.println("I got " + ingredientName);
    }
}