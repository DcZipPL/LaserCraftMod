package tk.dczippl.lasercraft.fabric.util;

import net.fabricmc.loader.api.FabricLoader;

public class ExtendedCompatibility {
	public static boolean isTechRebornPresent(){
		return FabricLoader.getInstance().isModLoaded("techreborn");
	}
}
