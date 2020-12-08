package tk.dczippl.lasercraft.fabric.util;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tk.dczippl.lasercraft.fabric.components.IntegerComponent;

public class BreakData {

	public static class BreakDataComponent implements IntegerComponent {
		int progress = 0;

		@Override
		public void readFromNbt(CompoundTag compoundTag) {
			progress = compoundTag.getInt("progress");
		}

		@Override
		public void writeToNbt(CompoundTag compoundTag) {
			compoundTag.putInt("progress",progress);
		}

		@Override
		public int getValue() {
			return progress;
		}

		@Override
		public void setValue(int value) {
			progress = value;
		}
	}

	public static BlockBreaker getBlockBreaker(World world) {
		return new BlockBreaker(world);
	}

	public static class BlockBreaker {
		World world;

		private BlockBreaker(World world){
			this.world = world;
		}

		public void breakBlock(BlockPos position, Entity breakingEntity, boolean drop){
			/*if (ModComponent.BREAKDATA.get(position).getValue() == 20) {
				ModComponent.BREAKDATA.get(position).setValue(0);*/
			if (world.random.nextInt(20)==2)
				world.breakBlock(position, drop, breakingEntity);
			//} else ModComponent.BREAKDATA.get(position).setValue(ModComponent.BREAKDATA.get(position).getValue()+1);
		}

		public void breakBlock(BlockPos position, boolean drop){
			/*if (ModComponent.BREAKDATA.get(position).getValue() == 20) {
				ModComponent.BREAKDATA.get(position).setValue(0);*/
			if (world.random.nextInt(20)==2)
				world.breakBlock(position, drop);
			//} else ModComponent.BREAKDATA.get(position).setValue(ModComponent.BREAKDATA.get(position).getValue()+1);
		}

		public void breakBlock(BlockPos position){
			/*if (ModComponent.BREAKDATA.get(position).getValue() == 20) {
				ModComponent.BREAKDATA.get(position).setValue(0);*/
			if (world.random.nextInt(20)==2)
				world.breakBlock(position, true);
			//} else ModComponent.BREAKDATA.get(position).setValue(ModComponent.BREAKDATA.get(position).getValue()+1);
		}
	}
}
