package com.codecool.kitchenmanagement;

import java.util.Random;

/**
 * Ingredients used for cooking.
 */
public enum Ingredient {
    MEAT,
    POTATO,
    CARROT;

    private static final Random RANDOM = new Random();

    /**
     * Return a randomly chosen ingredient.
     *
     * @return one of the ingredients.
     */
    public static Ingredient randomIngredient() {
        var allIngredients = Ingredient.values();
        var numOfIngredients = allIngredients.length;
        return allIngredients[RANDOM.nextInt(numOfIngredients)];
    }
}
