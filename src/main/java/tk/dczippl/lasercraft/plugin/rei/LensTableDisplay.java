package tk.dczippl.lasercraft.plugin.rei;

import com.google.common.collect.Lists;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.RecipeDisplay;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import tk.dczippl.lasercraft.fabric.items.ModItems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LensTableDisplay implements RecipeDisplay {
	private final List<ItemStack> border;
	private final List<ItemStack> modifier;
	private final List<ItemStack> glass;

	public LensTableDisplay(List<ItemStack> border, List<ItemStack> modifier, List<ItemStack> glass) {
		this.border = border;
		this.modifier = modifier;
		this.glass = glass;
	}

	@Override
	public @NotNull List<List<EntryStack>> getInputEntries() {
		return Lists.newArrayList(EntryStack.ofItemStacks(border),EntryStack.ofItemStacks(modifier),EntryStack.ofItemStacks(glass));
	}

	@Override
	public @NotNull List<List<EntryStack>> getRequiredEntries() {
		return getInputEntries();
	}

	@Override
	public @NotNull List<List<EntryStack>> getResultingEntries() {
		return Collections.singletonList(Collections.singletonList(EntryStack.create(new ItemStack(ModItems.LENS))));
	}

	@Override
	public @NotNull Identifier getRecipeCategory() {
		return LensTableCategory.ID;
	}
}
