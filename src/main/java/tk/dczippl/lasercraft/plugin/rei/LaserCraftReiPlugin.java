package tk.dczippl.lasercraft.plugin.rei;

import com.google.common.collect.Lists;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.RecipeHelper;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import me.shedaniel.rei.utils.CollectionUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import tk.dczippl.lasercraft.LaserCraft;
import tk.dczippl.lasercraft.fabric.blocks.ModBlocks;
import tk.dczippl.lasercraft.fabric.init.ModTags;

import java.util.List;

public class LaserCraftReiPlugin implements REIPluginV0 {
	public static final Identifier PLUGIN = new Identifier(LaserCraft.MOD_ID, "lasercraft_plugin");

	public LaserCraftReiPlugin(){

	}

	@Override
	public Identifier getPluginIdentifier() {
		return PLUGIN;
	}

	@Override
	public void registerPluginCategories(RecipeHelper recipeHelper) {
		recipeHelper.registerCategory(new LensTableCategory());
	}

	@Override
	public void registerRecipeDisplays(RecipeHelper recipeHelper) {
		recipeHelper.registerDisplay(new LensTableDisplay(
				CollectionUtils.map(Lists.newArrayList(ModTags.LENS_BORDER.values()), ItemStack::new),
				CollectionUtils.map(Lists.newArrayList(ModTags.LENS_MODIFIER.values()), ItemStack::new),
				CollectionUtils.map(Lists.newArrayList(ModTags.LENS_GLASS.values()), ItemStack::new)));
		recipeHelper.removeAutoCraftButton(new Identifier("lasercraft:lenstable"));
	}

	@Override
	public void registerOthers(RecipeHelper recipeHelper) {
		recipeHelper.registerWorkingStations(new Identifier("lasercraft:lenstable"), EntryStack.create(ModBlocks.LENS_TABLE),EntryStack.create(ModBlocks.LENS_ASSEMBLER));
	}
}
