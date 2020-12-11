package tk.dczippl.lasercraft.test;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Pair;
import org.junit.Test;
import tk.dczippl.lasercraft.fabric.util.LensValues;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
	@Test
	public void LensValuesTest(){
		List<Pair<Block,Integer>> l = new ArrayList<>();
		l.add(new Pair<>(Blocks.GLASS,8));
		l.add(new Pair<>(Blocks.GLOWSTONE,16));
		l.add(new Pair<>(Blocks.DIRT,-1));
		LensValues lensValues = new LensValues(l);
		String s = LensValues.Serializer.serialize(lensValues);
		l.add(new Pair<>(Blocks.GLOWSTONE,16));
	}
}