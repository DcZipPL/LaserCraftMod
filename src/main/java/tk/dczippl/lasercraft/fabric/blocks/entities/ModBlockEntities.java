package tk.dczippl.lasercraft.fabric.blocks.entities;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tk.dczippl.lasercraft.LaserCraft;
import tk.dczippl.lasercraft.fabric.blocks.ModBlocks;

public class ModBlockEntities {
	public static void register() {}

	public static BlockEntityType<?> LASER
			= registerBlockEntity("laser_block",BlockEntityType.Builder.create(LaserBlockEntity::new, ModBlocks.LASER_BLOCK).build(null));
	public static BlockEntityType<?> LENS_TABLE
			= registerBlockEntity("lens_table",BlockEntityType.Builder.create(LaserBlockEntity::new, ModBlocks.LENS_TABLE).build(null));

	public static BlockEntityType<?> registerBlockEntity(String name, BlockEntityType<?> type)
	{
		Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(LaserCraft.MOD_ID, name), type);
		return type;
	}
}
