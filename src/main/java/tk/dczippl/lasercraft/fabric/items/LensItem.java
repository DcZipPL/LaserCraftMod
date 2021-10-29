package tk.dczippl.lasercraft.fabric.items;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import tk.dczippl.lasercraft.LaserCraft;

import java.util.List;

public class LensItem extends Item {
	public LensItem(Settings settings) {
		super(settings);
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		String color = stack.getOrCreateNbt().contains("color") ? String.valueOf(stack.getNbt().getInt("color")) : "0";
		String strength = stack.getOrCreateNbt().contains("strength") ? String.valueOf(stack.getNbt().getInt("strength")) : "0";
		String range = stack.getOrCreateNbt().contains("color") ? String.valueOf(stack.getNbt().getInt("range")) : "0";
		tooltip.add(new TranslatableText("tooltip.lasercraft.color").append(new LiteralText(color)));
		tooltip.add(new TranslatableText("tooltip.lasercraft.strength").append(new LiteralText(strength)));
		tooltip.add(new TranslatableText("tooltip.lasercraft.range").append(new LiteralText(range)));
		super.appendTooltip(stack, world, tooltip, context);
	}
}
