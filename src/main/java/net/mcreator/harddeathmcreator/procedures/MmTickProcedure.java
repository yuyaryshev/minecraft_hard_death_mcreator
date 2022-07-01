package net.mcreator.harddeathmcreator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.harddeathmcreator.network.HardDeathMcreatorModVariables;
import net.mcreator.harddeathmcreator.init.HardDeathMcreatorModMobEffects;

public class MmTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		String msg = "";
		double memento_mori_hp = 0;
		double mmlv = 0;
		double mm_minutes = 0;
		double mmlv0 = 0;
		double mmlv1 = 0;
		double mmticks = 0;
		double illnessInTicks = 0;
		mmlv0 = entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get())
				? _livEnt.getEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get()).getAmplifier()
				: 0;
		mmlv1 = mmlv0 + 1;
		mmticks = entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get())
				? _livEnt.getEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get()).getDuration()
				: 0;
		{
			double _setval = mmticks;
			entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.memento_mori_time_left = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (mmticks > 32000) {
			mm_minutes = mmticks / 1200;
			{
				String _setval = "Memento Mori Lvl " + mmlv1 + " " + new java.text.DecimalFormat("##").format(mm_minutes / 60) + ":"
						+ new java.text.DecimalFormat("##").format(mm_minutes % 60) + ":"
						+ new java.text.DecimalFormat("##").format((mmticks % 1200) / 20);
				entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.memento_mori_time_str = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			{
				String _setval = "";
				entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.memento_mori_time_str = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		memento_mori_hp = Math.max(2, 20 - 4 * mmlv1);
		illnessInTicks = HardDeathMcreatorModVariables.mementoMoriIlnessIntervalSeconds * 20;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > memento_mori_hp) {
			if (entity instanceof LivingEntity _entity)
				_entity.setHealth((float) memento_mori_hp);
		}
		if (mmticks % illnessInTicks <= 0) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.WEAKNESS);
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, (int) illnessInTicks, (int) (mmlv - 1)));
			if (mmlv1 >= 4) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int) illnessInTicks, 3));
			} else if (mmlv1 >= 3) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int) illnessInTicks, 2));
			} else if (mmlv1 >= 2) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int) illnessInTicks, 1));
			}
		}
		if (mmticks % (HardDeathMcreatorModVariables.mementoMoriBlackoutIntervalSeconds * 20) <= 0) {
			if (mmlv1 > 2) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.CONFUSION);
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION,
							(int) (HardDeathMcreatorModVariables.MapVariables.get(world).mementoMoriIlnessBlindnessDurationSeconds * 20
									* (mmlv1 - 2)),
							(int) (mmlv0 - 2)));
			}
			if (mmlv1 > 3) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.BLINDNESS);
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,
							(int) (HardDeathMcreatorModVariables.mementoMoriIlnessNauseaDurationSeconds * 20 * (mmlv1 - 3)), (int) (mmlv0 - 3)));
			}
		}
	}
}
