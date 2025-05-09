package com.caffeineaddict.caffeineaddictmode.util;

import com.caffeineaddict.caffeineaddictmode.ModItems;
import java.util.Arrays;
import java.util.Optional;
import net.minecraft.world.item.Item;

public enum IngredientType {
    COFFEE_BEANS(ModItems.COFFEE_BEANS.get(), "coffee_beans"),
    ICE(ModItems.ICE.get(), "ice");

    private final Item item;
    private final String name;

    IngredientType(Item item, String name) {
        this.item = item;
        this.name = name;
    }

    public Item getItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    public static Optional<IngredientType> fromItem(Item item) {
        return Arrays.stream(values())
                .filter(type -> type.getItem() == item)
                .findFirst();
    }
}
