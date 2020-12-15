package tk.dczippl.lasercraft.fabric.util;

import net.fabricmc.loader.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.resource.ResourceReloadListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.Set;

public class ModifiedProgress {
	private static int p = 0;
	public static int next = 0;

	private static Random rand = new Random();

	public static float getProgress(){
		p++;

		if (p % 100 == 0){
			Logger logger = LogManager.getLogger();
			logger.info("L: "+p);
		}

		return Math.max(p/100f/FabricLoader.INSTANCE.getAllMods().size(),1f);
	}
}
