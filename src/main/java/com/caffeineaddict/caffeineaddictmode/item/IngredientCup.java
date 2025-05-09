package com.caffeineaddict.caffeineaddictmode.item;


import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;

public class IngredientCup {
    private final List<String> ingredients = new ArrayList<>();

    public static IngredientCup from(ItemStack stack) {
        IngredientCup cup = new IngredientCup();
        if (stack.hasTag() && stack.getTag().contains("ingredients", Tag.TAG_LIST)) {
            ListTag list = stack.getTag().getList("ingredients", Tag.TAG_STRING);
            for (Tag t : list) {
                cup.ingredients.add(t.getAsString());
            }
        }
        return cup;
    }

    public void add(String ingredient) {
        ingredients.add(ingredient);
    }

    public void merge(IngredientCup other) {
        ingredients.addAll(other.ingredients);
    }

    public void applyTo(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        ListTag list = new ListTag();
        for (String ing : ingredients) {
            list.add(StringTag.valueOf(ing));
        }
        tag.put("ingredients", list);
        tag.putInt("ingredientCount", ingredients.size());
    }

    public boolean isEmpty() {
        return ingredients.isEmpty();
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}