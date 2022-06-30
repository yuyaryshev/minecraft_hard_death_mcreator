package net.mcreator.harddeathmcreator.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.network.chat.TextComponent;

import net.mcreator.harddeathmcreator.network.HardDeathMcreatorModVariables;
import net.mcreator.harddeathmcreator.init.HardDeathMcreatorModMobEffects;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MementoMoriWatchdogProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_time_left > 0
				&& (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_lv > 0
				&& !(entity instanceof LivingEntity _livEnt ? _livEnt.hasEffect(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get()) : false)) {
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get(),
						(int) (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_time_left,
						(int) (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_lv));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("You can't avoid death toll of Memento Mori."), (false));
		}
	}
}
