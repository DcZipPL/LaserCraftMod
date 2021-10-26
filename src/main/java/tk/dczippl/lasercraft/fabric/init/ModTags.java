package tk.dczippl.lasercraft.fabric.init;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import tk.dczippl.lasercraft.LaserCraft;

public class ModTags {
	public static final Tag<Item> LENS_MODIFIER = register("lens_modifier");
	public static final Tag<Item> LENS_BORDER = register("lens_border");
	public static final Tag<Item> LENS_GLASS = register("lens_glass");

	private ModTags(){}

	private static Tag<Item> register(String id) {
		return TagRegistry.item(new Identifier(LaserCraft.MOD_ID, id));
	}
}