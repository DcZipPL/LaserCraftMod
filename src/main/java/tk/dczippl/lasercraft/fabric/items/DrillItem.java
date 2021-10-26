package tk.dczippl.lasercraft.fabric.items;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterials;
import tk.dczippl.lasercraft.fabric.blocks.ModBlocks;

public class DrillItem extends ToolItem {
	public DrillItem() {
		super(ToolMaterials.IRON, new Item.Settings().maxDamage(ToolMaterials.WOOD.getDurability()));
	}

	@Override
	public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
		if (state.getBlock() == ModBlocks.LASER_BLOCK || state.getBlock() == ModBlocks.SENSOR_BLOCK || state.getBlock() == ModBlocks.REFLECTOR_BLOCK)
			return ToolMaterials.GOLD.getMiningSpeedMultiplier();
		return 0.4F;
	}
}