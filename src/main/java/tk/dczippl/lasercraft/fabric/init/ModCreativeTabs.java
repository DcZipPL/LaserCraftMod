package tk.dczippl.lasercraft.fabric.init;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import tk.dczippl.lasercraft.LaserCraft;
import tk.dczippl.lasercraft.fabric.blocks.ModBlocks;
import tk.dczippl.lasercraft.fabric.items.ModItems;

public class ModCreativeTabs {
	public static ItemGroup MAIN = FabricItemGroupBuilder.create(
			new Identifier(LaserCraft.MOD_ID, "items"))
			.icon(() -> new ItemStack(ModBlocks.LASER_BLOCK))
			.appendItems(stacks ->
			{
				for (Item i: ModItems.MOD_BLOCKS)
				{
					stacks.add(new ItemStack(i));
				}

				for (Item i: ModItems.MOD_ITEMS)
				{
					stacks.add(new ItemStack(i));
				}
			})
			.build();
}
