package tk.dczippl.lasercraft.fabric;

import net.fabricmc.loader.FabricLoader;

public class ExtendedCompatibility {
	public static boolean isTechRebornPresent(){
		return FabricLoader.INSTANCE.isModLoaded("techreborn");
	}
}
