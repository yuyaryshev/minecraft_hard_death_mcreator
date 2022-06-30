package net.mcreator.harddeathmcreator.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.harddeathmcreator.network.HardDeathMcreatorModVariables;
import net.mcreator.harddeathmcreator.init.HardDeathMcreatorModMobEffects;

public class MementoMoriOnEffectActiveTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double memento_mori_hp = 0;
		double mmlv = 0;
		if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get())
				? _livEnt.getEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get()).getDuration()
				: 0) % 1800 < 10) {
			{
				double _setval = entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get())
						? _livEnt.getEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get()).getAmplifier()
						: 0;
				entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.memento_mori_lv = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get())
						? _livEnt.getEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get()).getDuration()
						: 0;
				entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.memento_mori_time_left = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			mmlv = entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get())
					? _livEnt.getEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get()).getAmplifier()
					: 0;
			memento_mori_hp = 20 - 4 * mmlv;
			if (memento_mori_hp < 2) {
				memento_mori_hp = 2;
			}
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > memento_mori_hp) {
				if (entity instanceof LivingEntity _entity)
					_entity.setHealth((float) memento_mori_hp);
			}
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 1800, (int) mmlv));
			if (mmlv > 1) {
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1800, 1));
				if (mmlv > 2) {
					if (entity instanceof LivingEntity _entity)
						_entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, (int) (360 * mmlv), (int) mmlv));
					if (mmlv > 3) {
						if (entity instanceof LivingEntity _entity)
							_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, (int) (360 * mmlv), (int) mmlv));
						if (entity instanceof LivingEntity _entity)
							_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int) (720 * mmlv), 2));
					}
				}
			}
		}
	}
}
