package com.caffeineaddict.caffeineaddictmode.drink;

import com.caffeineaddict.caffeineaddictmode.ModCreativeTab;
import com.caffeineaddict.caffeineaddictmode.ModItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Drink extends Item {
    public Drink(int nutrition, float saturation, MobEffect effect, int duration, int amplifier) {
        super(new Item.Properties()
                .tab(ModCreativeTab.CAFFEINE_TAB)
                .stacksTo(1)
                .food(new FoodProperties.Builder()
                        .nutrition(nutrition)
                        .saturationMod(saturation)
                        .effect(() -> new MobEffectInstance(effect, duration * 20, amplifier), 1.0f)
                        .alwaysEat()
                        .build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide && entity instanceof Player player && !player.getAbilities().instabuild) {
            ItemStack drop = new ItemStack(ModItems.CUP.get());

            if (!player.getInventory().add(drop)) {
                player.drop(drop, false);
            }
        }

        return result;
    }
}
