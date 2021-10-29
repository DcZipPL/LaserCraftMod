package tk.dczippl.lasercraft.plugin.rei;

import com.google.common.collect.Lists;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.api.common.plugins.REIPlugin;
import me.shedaniel.rei.api.common.util.CollectionUtils;
import net.minecraft.item.ItemStack;
import tk.dczippl.lasercraft.fabric.blocks.ModBlocks;
import tk.dczippl.lasercraft.fabric.init.ModTags;

import java.util.List;

public class LaserCraftReiPlugin implements REIClientPlugin {
	public LaserCraftReiPlugin(){}
	
	@Override
	public void registerCategories(CategoryRegistry registry) {
		registry.add(new LensTableCategory());
		registry.addWorkstations(CategoryIdentifier.of(LensTableCategory.ID),
				EntryStack.of(VanillaEntryTypes.ITEM,new ItemStack(ModBlocks.LENS_TABLE)),EntryStack.of(VanillaEntryTypes.ITEM,new ItemStack(ModBlocks.LENS_ASSEMBLER)));
	}
	
	@Override
	public void registerDisplays(DisplayRegistry registry) {
		registry.add(new LensTableDisplay(
				CollectionUtils.map(Lists.newArrayList(ModTags.LENS_BORDER.values()), ItemStack::new),
				CollectionUtils.map(Lists.newArrayList(ModTags.LENS_MODIFIER.values()), ItemStack::new),
				CollectionUtils.map(Lists.newArrayList(ModTags.LENS_GLASS.values()), ItemStack::new)));
		//registry.removeAutoCraftButton(new Identifier("lasercraft:lenstable"));
	}
}