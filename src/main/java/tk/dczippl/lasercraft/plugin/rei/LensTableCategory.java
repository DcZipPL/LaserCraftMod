package tk.dczippl.lasercraft.plugin.rei;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import tk.dczippl.lasercraft.fabric.blocks.ModBlocks;

import java.util.List;
import java.util.Objects;

public class LensTableCategory implements DisplayCategory<LensTableDisplay> {
	public static Identifier ID = new Identifier("lasercraft","lenstable");
	
	@Override
	public Text getTitle() {
		return new TranslatableText("category.lasercraft.lenstable");
	}
	
	@Override
	public Renderer getIcon() {
		return EntryStacks.of(ModBlocks.LENS_TABLE);
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
		widgets.add(Widgets.createSlot(new Point(startPoint.x + 61 + 22, startPoint.y + 5)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());
		return widgets;
	}

	@Override
	public int getDisplayHeight() {
		return 36;
	}
	
	@Override
	public CategoryIdentifier<? extends LensTableDisplay> getCategoryIdentifier() {
		return CategoryIdentifier.of(ID);
	}
}