package net.mcreator.harddeathmcreator.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.harddeathmcreator.network.HardDeathMcreatorModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MmRespawnProcedure {
	@SubscribeEvent
	public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
		execute(event, event.getPlayer().level, event.getPlayer());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_time_left > 0
				&& (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_lv > 0) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeAllEffects();
		}
		if (entity instanceof LivingEntity _entity)
			_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,
					(int) (((entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_lv - 1)
							* HardDeathMcreatorModVariables.MapVariables.get(world).mementoMoriIlnessBlindnessDurationSeconds * 20 * 3),
					(int) (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_lv));
		if (entity instanceof LivingEntity _entity)
			_entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION,
					(int) (((entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_lv - 1)
							* HardDeathMcreatorModVariables.mementoMoriIlnessNauseaDurationSeconds * 20 * 3),
					(int) (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_lv));
		if (entity instanceof Player _player)
			_player.getFoodData().setFoodLevel(5);
	}
}
