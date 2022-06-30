package net.mcreator.harddeathmcreator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.harddeathmcreator.init.HardDeathMcreatorModMobEffects;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class ClearMementoMoriCommandExecutedProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap cmdparams) {
		if (entity == null || cmdparams == null)
			return;
		if ((cmdparams.containsKey("0") ? cmdparams.get("0").toString() : "").length() > 0) {
			{
				List<? extends Player> _players = new ArrayList<>(world.players());
				for (Entity entityiterator : _players) {
					if ((entityiterator.getDisplayName().getString()).equals(cmdparams.containsKey("0") ? cmdparams.get("0").toString() : "")) {
						if (entityiterator instanceof LivingEntity _entity)
							_entity.removeEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get());
					}
				}
			}
		} else {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get());
		}
	}
}
