package net.mcreator.harddeathmcreator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.harddeathmcreator.network.HardDeathMcreatorModVariables;
import net.mcreator.harddeathmcreator.init.HardDeathMcreatorModMobEffects;

public class MementoMoriOnEffectActiveTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double memento_mori_hp = 0;
		double mmlv = 0;
		if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get())
				? _livEnt.getEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get()).getDuration()
				: 0) % 10 <= 0) {
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
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get())
					? _livEnt.getEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get()).getDuration()
					: 0) % HardDeathMcreatorModVariables.mementoMoriIlnessInterval <= 0) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.WEAKNESS);
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(
							new MobEffectInstance(MobEffects.WEAKNESS, (int) HardDeathMcreatorModVariables.mementoMoriIlnessInterval, (int) mmlv));
				if (mmlv >= 4) {
					if (entity instanceof LivingEntity _entity)
						_entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
					if (entity instanceof LivingEntity _entity)
						_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,
								(int) HardDeathMcreatorModVariables.mementoMoriIlnessInterval, 3));
				} else if (mmlv >= 3) {
					if (entity instanceof LivingEntity _entity)
						_entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
					if (entity instanceof LivingEntity _entity)
						_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,
								(int) HardDeathMcreatorModVariables.mementoMoriIlnessInterval, 2));
				} else if (mmlv >= 2) {
					if (entity instanceof LivingEntity _entity)
						_entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
					if (entity instanceof LivingEntity _entity)
						_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,
								(int) HardDeathMcreatorModVariables.mementoMoriIlnessInterval, 1));
				}
			}
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get())
					? _livEnt.getEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get()).getDuration()
					: 0) % HardDeathMcreatorModVariables.mementoMoriBlackoutInterval <= 0) {
				if (mmlv > 2) {
					if (entity instanceof LivingEntity _entity)
						_entity.removeEffect(MobEffects.CONFUSION);
					if (entity instanceof LivingEntity _entity)
						_entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION,
								(int) (HardDeathMcreatorModVariables.mementoMoriIlnessNauseaDuration * (mmlv - 2)), (int) (mmlv - 2)));
				}
				if (mmlv > 3) {
					if (entity instanceof LivingEntity _entity)
						_entity.removeEffect(MobEffects.BLINDNESS);
					if (entity instanceof LivingEntity _entity)
						_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,
								(int) (HardDeathMcreatorModVariables.MapVariables.get(world).mementoMoriIlnessBlindnessDuration * (mmlv - 3)),
								(int) (mmlv - 3)));
				}
			}
		}
	}
}
