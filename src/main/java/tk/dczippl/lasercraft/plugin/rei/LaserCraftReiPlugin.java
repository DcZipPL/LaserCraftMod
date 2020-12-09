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
		recipeHelper.registerDisplay(new LensTableDisplay(CollectionUtils.map(Lists.newArrayList(ItemTags.getTagGroup().getTag(new Identifier(LaserCraft.MOD_ID,"lens_modifier")).values()), ItemStack::new)));
	}

	@Override
	public void registerOthers(RecipeHelper recipeHelper) {
		recipeHelper.registerWorkingStations(new Identifier("lasercraft:lenstable"), new EntryStack[]{EntryStack.create(ModBlocks.LENS_TABLE)});
	}
}
