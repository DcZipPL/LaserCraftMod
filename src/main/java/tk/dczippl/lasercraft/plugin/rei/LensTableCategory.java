package tk.dczippl.lasercraft.plugin.rei;

import com.google.common.collect.Lists;
import me.shedaniel.clothconfig2.ClothConfigInitializer;
import me.shedaniel.clothconfig2.api.ScissorsHandler;
import me.shedaniel.clothconfig2.api.ScrollingContainer;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.REIHelper;
import me.shedaniel.rei.api.RecipeCategory;
import me.shedaniel.rei.api.widgets.Slot;
import me.shedaniel.rei.api.widgets.Tooltip;
import me.shedaniel.rei.api.widgets.Widgets;
import me.shedaniel.rei.gui.entries.RecipeEntry;
import me.shedaniel.rei.gui.widget.Widget;
import me.shedaniel.rei.gui.widget.WidgetWithBounds;
import me.shedaniel.rei.utils.CollectionUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import tk.dczippl.lasercraft.fabric.blocks.ModBlocks;

import java.util.List;
import java.util.Objects;

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
	public @NotNull List<Widget> setupDisplay(LensTableDisplay display, Rectangle bounds) {
		Point startPoint = new Point(bounds.getCenterX() - 31, bounds.getCenterY() - 13);
		List<Widget> widgets = Lists.newArrayList();
		widgets.add(Widgets.createRecipeBase(bounds));
		widgets.add(Widgets.createArrow(new Point(startPoint.x + 27 + 22, startPoint.y + 4)));
		widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 61 + 22, startPoint.y + 5)));
		widgets.add(Widgets.createSlot(new Point(startPoint.x + 4 - 22, startPoint.y + 5)).entries(display.getInputEntries().get(0)).markInput());
		widgets.add(Widgets.createSlot(new Point(startPoint.x + 4, startPoint.y + 5)).entries(display.getInputEntries().get(1)).markInput());
		widgets.add(Widgets.createSlot(new Point(startPoint.x + 4 + 22, startPoint.y + 5)).entries(display.getInputEntries().get(2)).markInput());
		widgets.add(Widgets.createSlot(new Point(startPoint.x + 61 + 22, startPoint.y + 5)).entries(display.getResultingEntries().get(0)).disableBackground().markOutput());
		return widgets;
	}

	@Override
	public int getDisplayHeight() {
		return 36;
	}
}
