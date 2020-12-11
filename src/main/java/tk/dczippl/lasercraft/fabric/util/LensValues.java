package tk.dczippl.lasercraft.fabric.util;

import com.google.gson.Gson;
import net.minecraft.block.Block;
import net.minecraft.util.Pair;

import java.util.HashMap;
import java.util.List;

public class LensValues {
	protected List<Pair<Block,Integer>> map;

	public LensValues(List<Pair<Block,Integer>> values){
		this.map = values;
	}

	public static int getGlassFromJson(Block block) {

		return 0;
	}

	public static class Serializer{
		public static String serialize(LensValues values){
			Gson gson = new Gson();
			return gson.toJson(values);
		}
	}
}
