
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.harddeathmcreator.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.mcreator.harddeathmcreator.potion.MementoMoriMobEffect;
import net.mcreator.harddeathmcreator.HardDeathMcreatorMod;

public class HardDeathMcreatorModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, HardDeathMcreatorMod.MODID);
	public static final RegistryObject<MobEffect> MEMENTO_MORI = REGISTRY.register("memento_mori", () -> new MementoMoriMobEffect());
}
