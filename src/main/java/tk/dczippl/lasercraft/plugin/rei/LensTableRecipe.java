package tk.dczippl.lasercraft.plugin.rei;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import tk.dczippl.lasercraft.LaserCraft;

public class LensTableRecipe implements Recipe<Inventory> {
	@Override
	public boolean matches(Inventory inv, World world) {
		return true;
	}

	@Override
	public ItemStack craft(Inventory inv) {
		return null;
	}

	@Override
	public boolean fits(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getOutput() {
		return null;
	}

	@Override
	public Identifier getId() {
		return new Identifier(LaserCraft.MOD_ID,"lens");
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return null;
	}

	@Override
	public RecipeType<?> getType() {
		return null;
	}
}
