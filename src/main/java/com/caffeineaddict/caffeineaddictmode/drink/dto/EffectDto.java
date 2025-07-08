package com.caffeineaddict.caffeineaddictmode.drink.dto;

import net.minecraft.world.effect.MobEffect;

public class EffectDto {
    private final MobEffect effect;
    private final int duration;

    public EffectDto(MobEffect effect, int duration) {
        this.effect = effect;
        this.duration = duration;
    }

    public MobEffect getEffect() {
        return effect;
    }

    public int getDuration() {
        return duration;
    }
}
