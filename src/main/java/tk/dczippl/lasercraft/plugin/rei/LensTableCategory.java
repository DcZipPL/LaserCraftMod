package tk.dczippl.lasercraft.plugin.rei;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.RecipeCategory;
import me.shedaniel.rei.api.widgets.Slot;
import me.shedaniel.rei.api.widgets.Tooltip;
import me.shedaniel.rei.api.widgets.Widgets;
import me.shedaniel.rei.gui.entries.RecipeEntry;
import me.shedaniel.rei.gui.widget.Widget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import tk.dczippl.lasercraft.fabric.blocks.ModBlocks;

import java.util.List;

public class LensTableCategory implements RecipeCategory<LensTableDisplay> {
	public static Identifier ID = new Identifier("lasercraft","lenstable");

	@Override
	public @NotNull Identifier getIdentifier() {
		return ID;
	}

	@Override
	public @NotNull String getCategoryName() {
		return I18n.translate("category.lasercraft.lenstable");
	}

	@Override
	public @NotNull EntryStack getLogo() {
		return EntryStack.create(ModBlocks.LENS_TABLE);
	}

	@Override
	public @NotNull List<Widget> setupDisplay(LensTableDisplay recipeDisplay, Rectangle bounds) {
		Point startPoint = new Point(bounds.getCenterX() - 41, bounds.getCenterY() - 17);
		String burnItems = "none";//String.valueOf(recipeDisplay.getFuelTime());
		List<Widget> widgets = Lists.newArrayList();
		widgets.add(Widgets.createRecipeBase(bounds));
		widgets.add(Widgets.createLabel(new Point(bounds.x + 26, bounds.getMaxY() - 15), new TranslatableText("category.rei.fuel.time.items", burnItems))
				.color(0xFF404040, 0xFFBBBBBB).noShadow().leftAligned());
		widgets.add(Widgets.createBurningFire(new Point(bounds.x + 6, startPoint.y + 1)).animationDurationTicks(60));
		widgets.add(Widgets.createSlot(new Point(bounds.x + 6, startPoint.y + 18)).entries(recipeDisplay.getInputEntries().get(0)).markInput());
		return widgets;
	}

	@Override
	public @NotNull RecipeEntry getSimpleRenderer(LensTableDisplay recipe) {
		Slot slot = Widgets.createSlot(new Point(0, 0)).entries(recipe.getInputEntries().get(0)).disableBackground().disableHighlight();
		String burnItems = "none0";
		return new RecipeEntry() {
			private TranslatableText text = new TranslatableText("category.rei.fuel.time_short.items", burnItems);

			@Override
			public int getHeight() {
				return 22;
			}

			@Override
			public Tooltip getTooltip(Point point) {
				if (slot.containsMouse(point))
					return slot.getCurrentTooltip(point);
				return null;
			}

			@Override
			public void render(MatrixStack matrices, Rectangle bounds, int mouseX, int mouseY, float delta) {
				slot.setZ(getZ() + 50);
				slot.getBounds().setLocation(bounds.x + 4, bounds.y + 2);
				slot.render(matrices, mouseX, mouseY, delta);
				MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, text.asOrderedText(), bounds.x + 25, bounds.y + 8, -1);
			}
		};
	}
}
