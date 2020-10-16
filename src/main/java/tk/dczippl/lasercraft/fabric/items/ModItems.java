package tk.dczippl.lasercraft.fabric.items;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tk.dczippl.lasercraft.LaserCraft;

import java.util.ArrayList;

public class ModItems {
	public static final ArrayList<Item> MOD_BLOCKS = new ArrayList<Item>();
	public static final ArrayList<Item> MOD_ITEMS = new ArrayList<Item>();

	public static final Item REDSTONE_CRYSTAL = registerItem("redstone_crystal", new Item(new Item.Settings()));
	public static final Item LENS = registerItem("lens", new LensItem(new Item.Settings()));

	public static void register() {}

	public static Item registerItem(String name, Item item)
	{
		Registry.register(Registry.ITEM, new Identifier(LaserCraft.MOD_ID, name), item);
		if (item instanceof BlockItem)
			MOD_BLOCKS.add(item);
		else
			MOD_ITEMS.add(item);
		return item;
	}
}
