package net.mcreator.harddeathmcreator.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;

import net.mcreator.harddeathmcreator.network.HardDeathMcreatorModVariables;
import net.mcreator.harddeathmcreator.HardDeathMcreatorMod;

public class MmFinishProcedure {
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
			HardDeathMcreatorMod.LOGGER
					.warn(("mm_expire old=" + (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_time_left + "new=" + 0));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(
						new TextComponent(("mm_expire old=" + (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_time_left + "new=" + 0)),
						(false));
		}
	}
}
