package tk.dczippl.lasercraft.fabric.blocks.entities;

import net.minecraft.block.entity.BlockEntityType;
import tk.dczippl.lasercraft.fabric.blocks.ModBlocks;

public class ModBlockEntities {
	public static void register() {}

	public static BlockEntityType<LaserBlockEntity> LASER = BlockEntityType.Builder.create(LaserBlockEntity::new, ModBlocks.LASER_BLOCK).build(null);
}
