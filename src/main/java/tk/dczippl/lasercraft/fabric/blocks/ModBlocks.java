package tk.dczippl.lasercraft.fabric.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tk.dczippl.lasercraft.LaserCraft;
import tk.dczippl.lasercraft.fabric.ExtendedCompatibility;
import tk.dczippl.lasercraft.fabric.init.ModCreativeTabs;
import tk.dczippl.lasercraft.fabric.items.ModItems;

public class ModBlocks {

	public static final Block LASER_BLOCK = registerBlock("laser_block", new LaserBlock());
	public static final Block LENS_TABLE = registerBlock("lens_table", new LensTableBlock());
	public static final Block LENS_ASSEMBLER = registerMachine("lens_assembler");

	public static void register() {}

	public static Block registerBlock(String name, Block block)
	{
		Registry.register(Registry.BLOCK, new Identifier(LaserCraft.MOD_ID, name), block);
		ModItems.registerItem(name, new BlockItem(block, new Item.Settings().group(ModCreativeTabs.MAIN)));
		return block;
	}

	private static Block registerMachine(String name) {
		if (ExtendedCompatibility.isTechRebornPresent())
			return new LensAssemblerBlock();
		else return Blocks.AIR;
	}
}
