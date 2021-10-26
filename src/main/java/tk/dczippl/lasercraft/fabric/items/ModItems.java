package tk.dczippl.lasercraft.fabric.items;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tk.dczippl.lasercraft.LaserCraft;
import tk.dczippl.lasercraft.fabric.util.ExtendedCompatibility;

import java.util.ArrayList;

public class ModItems {
	public static final ArrayList<Item> MOD_BLOCKS = new ArrayList<Item>();
	public static final ArrayList<Item> MOD_ITEMS = new ArrayList<Item>();

	public static final Item REDSTONE_CRYSTAL = registerItem("redstone_crystal", new CoilItem(new Item.Settings()));
	public static final Item LENS = registerItem("lens", new LensItem(new Item.Settings()));

	public static final Item REDSTONE_DRILL = registerItem("redstone_drill", new DrillItem());
	public static final Item LASER_REMOTE = registerItem("laser_remote", new Item(new Item.Settings()));

	public static final Item YTTRIUM_INGOT = registerItem("yttrium_ingot", new Item(new Item.Settings()));
	public static final Item YTTRIUM_PLATE = registerTechrebornItem("yttrium_plate", new Item(new Item.Settings()));
	public static final Item YTTRIUM_DUST = registerTechrebornItem("yttrium_dust", new Item(new Item.Settings()));
	public static final Item YTTRIUM_SMALL_DUST = registerTechrebornItem("yttrium_small_dust", new Item(new Item.Settings()));

	public static void register() {}

	public static Item registerItem(String name, Item item)
	{
		// Register mod items
		Registry.register(Registry.ITEM, new Identifier(LaserCraft.MOD_ID, name), item);
		if (item instanceof BlockItem){
			MOD_BLOCKS.add(item);
		}
		else{
			MOD_ITEMS.add(item);
		}
		return item;
	}

	public static Item registerTechrebornItem(String name, Item item) {
		if (ExtendedCompatibility.isTechRebornPresent())
			return registerItem(name, item);
		else return Items.AIR;
	}
}
