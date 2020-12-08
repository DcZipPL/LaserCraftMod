package tk.dczippl.lasercraft.plugin.rei;

import com.google.common.collect.Lists;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.TransferRecipeDisplay;
import me.shedaniel.rei.server.ContainerInfo;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import tk.dczippl.lasercraft.fabric.items.ModItems;

import java.util.ArrayList;
import java.util.List;

public class LensTableRecipeWrapper implements TransferRecipeDisplay {
	private final List<List<EntryStack>> inputs;

	@Override
	public int getWidth() {
		return 1;
	}

	@Override
	public int getHeight() {
		return 1;
	}

	@Override
	public List<List<EntryStack>> getOrganisedInputEntries(ContainerInfo<ScreenHandler> containerInfo, ScreenHandler screenHandler) {
		return inputs;
	}

	public LensTableRecipeWrapper(LensTableRecipe recipe) {
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
		return LensTableCategory.ID;
	}
}
