package tk.dczippl.lasercraft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import tk.dczippl.lasercraft.fabric.screens.LaserScreen;
import tk.dczippl.lasercraft.fabric.screens.LensTableScreen;

@Environment(EnvType.CLIENT)
public class ClientLaserCraft implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ScreenRegistry.register(LaserCraft.LASER_SCREEN_HANDLER, LaserScreen::new);
		ScreenRegistry.register(LaserCraft.LENS_TABLE_SCREEN_HANDLER, LensTableScreen::new);
	}
}
