package tk.dczippl.lasercraft.plugin.rei;

import com.google.common.collect.Lists;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.ItemStack;
import tk.dczippl.lasercraft.fabric.items.ModItems;

import java.util.Collections;
import java.util.List;

public class LensTableDisplay implements Display {
	private final List<ItemStack> border;
	private final List<ItemStack> modifier;
	private final List<ItemStack> glass;

	public LensTableDisplay(List<ItemStack> border, List<ItemStack> modifier, List<ItemStack> glass) {
		this.border = border;
		this.modifier = modifier;
		this.glass = glass;
	}

	@Override
	public List<EntryIngredient> getInputEntries() {
		return Lists.newArrayList(EntryIngredients.ofItemStacks(border),EntryIngredients.ofItemStacks(modifier),EntryIngredients.ofItemStacks(glass));
	}
	
	@Override
	public List<EntryIngredient> getOutputEntries() {
		return Collections.singletonList(EntryIngredient.of(EntryStacks.of(new ItemStack(ModItems.LENS))));
	}
	
	@Override
	public List<EntryIngredient> getRequiredEntries() {
		return getInputEntries();
	}
	
	@Override
	public CategoryIdentifier<?> getCategoryIdentifier() {
		return CategoryIdentifier.of(LensTableCategory.ID);
	}
}
