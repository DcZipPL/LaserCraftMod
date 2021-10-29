package tk.dczippl.lasercraft.fabric.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

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

		return Math.max(p/100f/net.fabricmc.loader.api.FabricLoader.getInstance().getAllMods().size(),1f);
	}
}
