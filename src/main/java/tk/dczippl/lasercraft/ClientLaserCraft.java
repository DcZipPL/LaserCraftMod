package tk.dczippl.lasercraft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;
import tk.dczippl.lasercraft.fabric.items.ModItems;
import tk.dczippl.lasercraft.fabric.screens.LaserScreen;
import tk.dczippl.lasercraft.fabric.screens.LensTableScreen;

@Environment(EnvType.CLIENT)
public class ClientLaserCraft implements ClientModInitializer {

	@SuppressWarnings({"unchecked", "ConstantConditions"})
	@Override
	public void onInitializeClient() {
		FabricModelPredicateProviderRegistry.register(ModItems.LENS, new Identifier("color"),(stack, world, entity, seed) -> {
			if (stack.getOrCreateNbt().contains("color")){
				return stack.getNbt().getInt("color");
			}
			return 0;
		});
		FabricModelPredicateProviderRegistry.register(ModItems.LENS, new Identifier("border"),(stack, world, entity, seed) -> {
			if (stack.getOrCreateNbt().contains("border")){
				return stack.getNbt().getInt("border");
			}
			return 0;
		});
		FabricModelPredicateProviderRegistry.register(ModItems.LENS, new Identifier("mode"),(stack, world, entity, seed) -> {
			if (stack.getOrCreateNbt().contains("mode")){
				return stack.getNbt().getInt("mode");
			}
			return 0;
		});

		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
			String[] borders = {"stone","stonebricks","netherbrick","cobblestone", "sandstone","planks","log","gold","iron","diamond","wool"};
			String[] colors = {"white","red","blue","cyan", "green","yellow","purple","black","orange"};
			for (String color : colors) {
				registry.register(LaserCraft.idFrom("item/part/" + color + "_lens"));
				registry.register(LaserCraft.idFrom("block/part/" + color + "_lens_hole"));
			}
			for (String border : borders)
				registry.register(LaserCraft.idFrom("item/part/lens_border_"+border+"_basic"));
		});

		ScreenRegistry.register(LaserCraft.LASER_SCREEN_HANDLER, LaserScreen::new);
		ScreenRegistry.register(LaserCraft.LENS_TABLE_SCREEN_HANDLER, LensTableScreen::new);
	}
}
