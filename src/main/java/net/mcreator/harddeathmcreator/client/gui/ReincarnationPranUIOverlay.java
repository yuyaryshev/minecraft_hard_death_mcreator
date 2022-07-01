
package net.mcreator.harddeathmcreator.client.gui;

import org.checkerframework.checker.units.qual.h;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.harddeathmcreator.procedures.ShouldShowPranaProcedure;
import net.mcreator.harddeathmcreator.procedures.ShouldShowMmDurationProcedure;
import net.mcreator.harddeathmcreator.procedures.ShouldShowDeathUIProcedure;
import net.mcreator.harddeathmcreator.network.HardDeathMcreatorModVariables;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class ReincarnationPranUIOverlay {
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(ScreenEvent.DrawScreenEvent.Post event) {
		if (event.getScreen() instanceof InventoryScreen) {
			int w = event.getScreen().width;
			int h = event.getScreen().height;
			int posX = w / 2;
			int posY = h / 2;
			Level _world = null;
			double _x = 0;
			double _y = 0;
			double _z = 0;
			Player entity = Minecraft.getInstance().player;
			if (entity != null) {
				_world = entity.level;
				_x = entity.getX();
				_y = entity.getY();
				_z = entity.getZ();
			}
			Level world = _world;
			double x = _x;
			double y = _y;
			double z = _z;
			RenderSystem.disableDepthTest();
			RenderSystem.depthMask(false);
			RenderSystem.enableBlend();
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
					GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			RenderSystem.setShaderColor(1, 1, 1, 1);
			if (ShouldShowDeathUIProcedure.execute(entity)) {
				if (ShouldShowPranaProcedure.execute(entity)) {
					RenderSystem.setShaderTexture(0, new ResourceLocation("hard_death_mcreator:textures/reincarnation_prana_gauge.png"));
					Minecraft.getInstance().gui.blit(event.getPoseStack(), posX + -90, posY + 79, 0, 0, 32, 4, 32, 4);
				}
				if (ShouldShowMmDurationProcedure.execute(entity))
					Minecraft.getInstance().font.draw(event.getPoseStack(),
							"" + ((entity.getCapability(HardDeathMcreatorModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new HardDeathMcreatorModVariables.PlayerVariables())).memento_mori_time_str) + "",
							posX + 70, posY + -120, -1);
			}
			RenderSystem.depthMask(true);
			RenderSystem.defaultBlendFunc();
			RenderSystem.enableDepthTest();
			RenderSystem.disableBlend();
			RenderSystem.setShaderColor(1, 1, 1, 1);
		}
	}
}
