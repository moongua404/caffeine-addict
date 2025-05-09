package com.caffeineaddict.caffeineaddictmode;

import com.caffeineaddict.caffeineaddictmode.item.IngredientCup;
import com.caffeineaddict.caffeineaddictmode.util.IngredientType;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CaffeineAddictMode.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    @SubscribeEvent
    public static void onCrafting(PlayerEvent.ItemCraftedEvent event) {
        ItemStack result = event.getCrafting();
        Container inv = event.getInventory();

        if (result.getItem() == ModItems.EMPTY_CUP.get()) {
            IngredientCup resultCup = new IngredientCup();
            AtomicBoolean addedNewIngredients = new AtomicBoolean(false);

            for (int i = 0; i < inv.getContainerSize(); i++) {
                ItemStack item = inv.getItem(i);
                if (item.isEmpty()) {
                    continue;
                }

                if (item.getItem() == ModItems.EMPTY_CUP.get()) {
                    resultCup.merge(IngredientCup.from(item));
                }

                IngredientType.fromItem(item.getItem()).ifPresent(type -> {
                    resultCup.add(type.getName());
                    addedNewIngredients.set(true);
                });
            }

            if (!addedNewIngredients.get()) {
                result.setTag(null);
            } else {
                resultCup.applyTo(result);
            }
        }
    }
}
