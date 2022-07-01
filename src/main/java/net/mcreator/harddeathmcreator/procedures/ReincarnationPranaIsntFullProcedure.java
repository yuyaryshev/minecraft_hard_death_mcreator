package net.mcreator.harddeathmcreator.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.harddeathmcreator.network.HardDeathMcreatorModVariables;

public class ReincarnationPranaIsntFullProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).reincarnationPrana < 100;
	}
}
