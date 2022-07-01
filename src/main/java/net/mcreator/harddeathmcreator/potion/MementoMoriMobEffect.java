
package net.mcreator.harddeathmcreator.potion;

import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.harddeathmcreator.procedures.MmTickProcedure;
import net.mcreator.harddeathmcreator.procedures.MmShouldTickProcedure;
import net.mcreator.harddeathmcreator.procedures.MmFinishProcedure;

public class MementoMoriMobEffect extends MobEffect {
	public MementoMoriMobEffect() {
		super(MobEffectCategory.HARMFUL, -13434778);
	}

	@Override
	public String getDescriptionId() {
		return "effect.hard_death_mcreator.memento_mori";
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		MmTickProcedure.execute(entity.level, entity);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		MmFinishProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return MmShouldTickProcedure.execute(duration);
	}
}
