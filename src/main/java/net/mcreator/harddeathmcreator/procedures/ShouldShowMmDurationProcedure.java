package net.mcreator.harddeathmcreator.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.harddeathmcreator.network.HardDeathMcreatorModVariables;

public class ShouldShowMmDurationProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_time_left >= 32000;
	}
}
