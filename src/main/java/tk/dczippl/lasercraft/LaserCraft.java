package tk.dczippl.lasercraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import tk.dczippl.lasercraft.fabric.blocks.ModBlocks;
import tk.dczippl.lasercraft.fabric.blocks.entities.ModBlockEntities;
import tk.dczippl.lasercraft.fabric.items.ModItems;
import tk.dczippl.lasercraft.fabric.screens.handlers.LaserScreenHandler;

public class LaserCraft implements ModInitializer {
	public static String MOD_ID = "lasercraft";

	public static Identifier idFrom(String location) {
		return new Identifier(MOD_ID,location);
	}

	public static final ScreenHandlerType<LaserScreenHandler> LASER_SCREEN_HANDLER;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModBlocks.register();
		ModItems.register();
	}

	static {
		//We use registerSimple here because our Entity is not an ExtendedScreenHandlerFactory
		//but a NamedScreenHandlerFactory.
		LASER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(idFrom("laser"), LaserScreenHandler::new);
	}
}