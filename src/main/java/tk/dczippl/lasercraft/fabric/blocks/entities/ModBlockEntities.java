package tk.dczippl.lasercraft.fabric.blocks.entities;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
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
			= registerBlockEntityIfTechrebornPresent("lens_assembler.json", LensAssemblerBlockEntity::new, ModBlocks.LENS_TABLE);

	private static <T extends BlockEntity> BlockEntityType<?> registerBlockEntityIfTechrebornPresent(String name, Supplier<? extends T> supplier, Block... blocks) {
		if (ExtendedCompatibility.isTechRebornPresent())
			return registerBlockEntity(name,supplier, blocks);
		else return null;
	}

	public static <T extends BlockEntity> BlockEntityType<?> registerBlockEntity(String name, Supplier<? extends T> supplier, Block... blocks)
	{
		BlockEntityType<?> type = BlockEntityType.Builder.create(supplier,blocks).build(null);
		Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(LaserCraft.MOD_ID, name), type);
		return type;
	}
}