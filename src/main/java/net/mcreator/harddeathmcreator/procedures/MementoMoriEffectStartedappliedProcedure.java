package net.mcreator.harddeathmcreator.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;

import net.mcreator.harddeathmcreator.network.HardDeathMcreatorModVariables;
import net.mcreator.harddeathmcreator.init.HardDeathMcreatorModMobEffects;

public class MementoMoriEffectStartedappliedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
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
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(new TextComponent(("You died. You now will suffer Memento Mori lv "
					+ (new java.text.DecimalFormat("#")
							.format(entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get())
									? _livEnt.getEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get()).getAmplifier()
									: 0))
					+ "for duration"
					+ (new java.text.DecimalFormat("#")
							.format(entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get())
									? _livEnt.getEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get()).getDuration()
									: 0))
					+ "You'd better stay alive while it's active or you'll suffer MUCH more.")), (true));
	}
}
