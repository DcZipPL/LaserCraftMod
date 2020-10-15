package tk.dczippl.lasercraft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.ActionResult;
import tk.dczippl.lasercraft.client.renderer.LaserBlockEntityRender;
import tk.dczippl.lasercraft.fabric.blocks.entities.LaserBlockEntity;
import tk.dczippl.lasercraft.fabric.blocks.entities.ModBlockEntities;
import tk.dczippl.lasercraft.fabric.screens.LaserScreen;
import tk.dczippl.lasercraft.fabric.screens.LensTableScreen;

@Environment(EnvType.CLIENT)
public class ClientLaserCraft implements ClientModInitializer {

	@SuppressWarnings("unchecked")
	@Override
	public void onInitializeClient() {
		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
			registry.register(LaserCraft.idFrom("block/part/white_lens_hole"));
		});

		ScreenRegistry.register(LaserCraft.LASER_SCREEN_HANDLER, LaserScreen::new);
		ScreenRegistry.register(LaserCraft.LENS_TABLE_SCREEN_HANDLER, LensTableScreen::new);

		BlockEntityRendererRegistry.INSTANCE.register((BlockEntityType<LaserBlockEntity>) ModBlockEntities.LASER, LaserBlockEntityRender::new);
	}
}
