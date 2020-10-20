package tk.dczippl.lasercraft.fabric.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BreakData {

	public static BlockBreaker getBlockBreaker(World world) {
		return new BlockBreaker(world);
	}

	public static class BlockBreaker {
		World world;

		private BlockBreaker(World world){
			this.world = world;
		}

		public void breakBlock(BlockPos position, Entity breakingEntity, boolean drop){
			world.breakBlock(position, drop, breakingEntity);
		}

		public void breakBlock(BlockPos position, boolean drop){
			world.breakBlock(position, drop);
		}

		public void breakBlock(BlockPos position){
			world.breakBlock(position, true);
		}
	}
}
