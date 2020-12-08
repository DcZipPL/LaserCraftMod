package tk.dczippl.lasercraft.plugin.rei;

import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.RecipeHelper;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import net.minecraft.util.Identifier;
import tk.dczippl.lasercraft.fabric.blocks.ModBlocks;

public class LaserCraftReiPlugin implements REIPluginV0 {
	public static final Identifier PLUGIN = new Identifier("lasercraft", "lasercraft_plugin");

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
		recipeHelper.registerRecipes(LensTableCategory.ID, LensTableRecipe.class, LensTableRecipeWrapper::new);
		recipeHelper.registerDisplay(new LensDisplay());
	}

	@Override
	public void registerOthers(RecipeHelper recipeHelper) {
		recipeHelper.registerWorkingStations(new Identifier("lasercraft:lenstable"), new EntryStack[]{EntryStack.create(ModBlocks.LENS_TABLE)});
	}
}
