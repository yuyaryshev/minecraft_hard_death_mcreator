package net.mcreator.harddeathmcreator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;

import net.mcreator.harddeathmcreator.network.HardDeathMcreatorModVariables;
import net.mcreator.harddeathmcreator.init.HardDeathMcreatorModMobEffects;
import net.mcreator.harddeathmcreator.HardDeathMcreatorMod;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class MmClearCmdProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap cmdparams) {
		if (entity == null || cmdparams == null)
			return;
		if ((cmdparams.containsKey("0") ? cmdparams.get("0").toString() : "").length() > 0) {
			{
				List<? extends Player> _players = new ArrayList<>(world.players());
				for (Entity entityiterator : _players) {
					if ((entityiterator.getDisplayName().getString()).equals(cmdparams.containsKey("0") ? cmdparams.get("0").toString() : "")) {
						HardDeathMcreatorMod.LOGGER.warn(("mm_on_clear_cmd2 old="
								+ (entityiterator.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_time_left
								+ "new=" + 0));
						if (entity instanceof Player _player && !_player.level.isClientSide())
							_player.displayClientMessage(
									new TextComponent(("mm_on_clear_cmd2 old="
											+ (entityiterator.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
													.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_time_left
											+ "new=" + 0)),
									(false));
						{
							double _setval = 0;
							entityiterator.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.memento_mori_lv = _setval;
								capability.syncPlayerVariables(entityiterator);
							});
						}
						{
							double _setval = 0;
							entityiterator.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.memento_mori_time_left = _setval;
								capability.syncPlayerVariables(entityiterator);
							});
						}
						if (entityiterator instanceof LivingEntity _entity)
							_entity.removeEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get());
					}
				}
			}
		} else {
			HardDeathMcreatorMod.LOGGER
					.warn(("mm_on_clear_cmd1 old=" + (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_time_left + "new=" + 0));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent(
						("mm_on_clear_cmd1 old=" + (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_time_left + "new=" + 0)),
						(false));
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
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get());
		}
	}
}
