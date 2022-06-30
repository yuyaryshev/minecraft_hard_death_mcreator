package net.mcreator.harddeathmcreator.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.harddeathmcreator.network.HardDeathMcreatorModVariables;
import net.mcreator.harddeathmcreator.init.HardDeathMcreatorModMobEffects;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MementoMoriOnDeathProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity());
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_lv + 1;
			entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.memento_mori_lv = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = 10 * 3600 * Math.pow(2, (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_lv - 1);
			entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.memento_mori_time_left = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (entity instanceof LivingEntity _entity)
			_entity.addEffect(new MobEffectInstance(HardDeathMcreatorModMobEffects.MEMENTO_MORI.get(),
					(int) (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_time_left,
					(int) (entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_lv));
	}
}
