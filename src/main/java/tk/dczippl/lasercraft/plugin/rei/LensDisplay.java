package tk.dczippl.lasercraft.plugin.rei;

import com.google.common.collect.Lists;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.RecipeDisplay;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import tk.dczippl.lasercraft.fabric.items.ModItems;

import java.util.ArrayList;
import java.util.List;

public class LensDisplay implements RecipeDisplay {
	private final List<List<EntryStack>> inputs;

	public LensDisplay() {
		List<List<EntryStack>> inputs = new ArrayList<>();
		inputs.add(Lists.newArrayList(EntryStack.create(ModItems.REDSTONE_CRYSTAL)));
		this.inputs = inputs;
	}

	@Override
	public @NotNull List<List<EntryStack>> getInputEntries() {
		return inputs;
	}

	@Override
	public @NotNull Identifier getRecipeCategory() {
		return new Identifier("lasercraft","lenstable");
	}
}
