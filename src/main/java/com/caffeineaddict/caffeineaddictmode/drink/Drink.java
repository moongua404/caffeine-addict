package com.caffeineaddict.caffeineaddictmode.drink;

import com.caffeineaddict.caffeineaddictmode.ModCreativeTab;
import com.caffeineaddict.caffeineaddictmode.ModItems;
import com.caffeineaddict.caffeineaddictmode.drink.dto.EffectDto;
import java.util.List;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * 음료 아이템을 정의.
 * 포션 효과 적용, 스택 불가능, 사용 후 빈 컵 드롭
 * <p>
 * {@code Coffee}에서 확장하여 사용
 *
 * @author @moongua404
 */
public class Drink extends Item {
    protected final List<EffectDto> effects;
    protected int amplifier;

    /**
     * Drink 생성자
     *
     * @param nutrition   음식의 포만감 수치
     * @param saturation  음식의 포화도 수치
     * @param effects     적용할 효과와 지속시간 쌍 (예: 이동 속도 향상 30초 등)
     * @param amplifier   효과 증폭 수치
     */
    public Drink(int nutrition, float saturation, List<EffectDto> effects, int amplifier) {
        super(new Item.Properties()
                .tab(ModCreativeTab.CAFFEINE_TAB)
                .stacksTo(1)
                .food(new FoodProperties.Builder()
                        .nutrition(nutrition)
                        .saturationMod(saturation)
                        .alwaysEat()
                        .build()));
        this.effects = List.copyOf(effects);
        this.amplifier = amplifier;
    }

    protected List<MobEffectInstance> createEffectInstances(ItemStack stack) {
        return effects.stream()
                .map(effect -> new MobEffectInstance(effect.getEffect(), effect.getDuration() * 20, amplifier))
                .toList();
    }

    /**
     * 아이템 사용 후 호출
     * 빈 컵을 지급 (크리에이티브 모드일 경우 지급하지 않음)
     */
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);
        if (!level.isClientSide) {
            createEffectInstances(stack).forEach(entity::addEffect);

            if (entity instanceof Player player && !player.getAbilities().instabuild) {
                ItemStack drop = new ItemStack(ModItems.CUP.get());
                if (!player.getInventory().add(drop)) {
                    player.drop(drop, false);
                }
            }
        }
        return result;
    }

    public void setAmplifier(int amplifier) {
        this.amplifier = amplifier;
    }
}
