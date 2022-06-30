package net.mcreator.harddeathmcreator.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.harddeathmcreator.network.HardDeathMcreatorModVariables;

public class MementoMoriEffectExpiresProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_time_left <= 20) {
			{
				double _setval = 0;
				entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.memento_mori_lv = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = 0;
				entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.memento_mori_time_left = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
