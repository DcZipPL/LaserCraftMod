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
	private final List<ItemStack> inputs;

	public LensTableDisplay(List<ItemStack> inputs) {
		this.inputs = inputs;
	}

	@Override
	public @NotNull List<List<EntryStack>> getInputEntries() {
		return Collections.singletonList(EntryStack.ofItemStacks(inputs));
	}

	@Override
	public @NotNull Identifier getRecipeCategory() {
		return LensTableCategory.ID;
	}
}
