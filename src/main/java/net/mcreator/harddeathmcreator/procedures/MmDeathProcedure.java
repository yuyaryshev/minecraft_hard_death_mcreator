package net.mcreator.harddeathmcreator.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.network.chat.TextComponent;

import net.mcreator.harddeathmcreator.network.HardDeathMcreatorModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MmDeathProcedure {
	@SubscribeEvent
	public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
		execute(event, event.getPlayer());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		double newDuration = 0;
		{
			double _setval = Math.min(5, (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_lv + 1);
			entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.memento_mori_lv = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		newDuration = HardDeathMcreatorModVariables.mementoMoriDurationSeconds * 20
				* Math.pow(HardDeathMcreatorModVariables.mementoMoriExpBase,
						(entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_lv - 1);
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(
					new TextComponent(
							("mm_on_death old="
									+ (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
											.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_time_left
									+ "new=" + newDuration)),
					(false));
		{
			double _setval = newDuration;
			entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.memento_mori_time_left = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (entity instanceof LivingEntity _entity)
			_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, (int) (10 * 20), 0));
		if (entity instanceof LivingEntity _entity)
			_entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, (int) (30 * 20), 0));
	}
}
