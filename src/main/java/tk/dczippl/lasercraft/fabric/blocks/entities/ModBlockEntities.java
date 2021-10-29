package tk.dczippl.lasercraft.fabric.blocks.entities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import tk.dczippl.lasercraft.LaserCraft;
import tk.dczippl.lasercraft.fabric.util.ExtendedCompatibility;
import tk.dczippl.lasercraft.fabric.blocks.ModBlocks;
import tk.dczippl.lasercraft.plugin.techreborn.LensAssemblerBlockEntity;

import java.util.function.Supplier;

public class ModBlockEntities {
	public static void register() {}

	public static BlockEntityType<?> LASER
			= registerBlockEntity("laser_block",LaserBlockEntity::new, ModBlocks.LASER_BLOCK);
	public static BlockEntityType<?> LENS_TABLE
			= registerBlockEntity("lens_table",LensTableBlockEntity::new, ModBlocks.LENS_TABLE);
	public static final BlockEntityType<?> LENS_ASSEMBLER
			= registerBlockEntityIfTechrebornPresent("lens_assembler", LensAssemblerBlockEntity::new, ModBlocks.LENS_TABLE);
	
	protected static <T extends BlockEntity> BlockEntityType<?> registerBlockEntityIfTechrebornPresent(String name, BlockEntityType.BlockEntityFactory<? extends T> factory, Block... blocks){
		if (ExtendedCompatibility.isTechRebornPresent())
			return registerBlockEntity(name,factory, blocks);
		else return null;
	}

	public static <T extends BlockEntity> BlockEntityType<?> registerBlockEntity(String name, BlockEntityType.BlockEntityFactory<? extends T> factory, Block... blocks)
	{
		BlockEntityType<?> type = BlockEntityType.Builder.create(factory,blocks).build(null);
		Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(LaserCraft.MOD_ID, name), type);
		return type;
	}
}