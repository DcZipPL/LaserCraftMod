package tk.dczippl.lasercraft.plugin.techreborn;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import reborncore.client.gui.builder.GuiBase;
import reborncore.client.gui.guibuilder.GuiBuilder;
import reborncore.client.screen.builder.BuiltScreenHandler;

public class LensAssemblerScreen extends GuiBase<BuiltScreenHandler> {
	static final Identifier RECIPE_BOOK_TEXTURE = new Identifier("textures/gui/recipe_book.png");
	boolean showGui = true;
	LensAssemblerBlockEntity blockEntityLensAssembler;

	public LensAssemblerScreen(int syncID, PlayerEntity player, LensAssemblerBlockEntity blockEntity) {
		super(player, blockEntity, blockEntity.createScreenHandler(syncID, player));
		this.blockEntityLensAssembler = blockEntity;
	}

	public void renderItemStack(ItemStack stack, int x, int y) {
		MinecraftClient.getInstance().getItemRenderer().renderInGuiWithOverrides(stack, x, y);
	}

	protected void drawForeground(MatrixStack matrixStack, int mouseX, int mouseY) {
		super.drawForeground(matrixStack, mouseX, mouseY);
		Layer layer = Layer.FOREGROUND;
		this.builder.drawProgressBar(matrixStack, this, this.blockEntityLensAssembler.getProgress(), this.blockEntityLensAssembler.getMaxProgress(), 120, 44, mouseX, mouseY, GuiBuilder.ProgressDirection.RIGHT, layer);
		this.builder.drawMultiEnergyBar(matrixStack, this, 9, 26, (int)this.blockEntityLensAssembler.getEnergy(), (int)this.blockEntityLensAssembler.getMaxStoredPower(), mouseX, mouseY, 0, layer);
	}

	protected void drawBackground(MatrixStack matrixStack, float f, int mouseX, int mouseY) {
		super.drawBackground(matrixStack, f, mouseX, mouseY);
		Layer layer = Layer.BACKGROUND;

		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
				this.drawSlot(matrixStack, 28 + i * 18, 25 + j * 18, layer);
			}
		}

		this.drawOutputSlot(matrixStack, 95, 42, layer);
		this.drawOutputSlot(matrixStack, 145, 42, layer);
		this.drawOutputSlot(matrixStack, 145, 70, layer);
		/*CraftingRecipe recipe = this.blockEntityLensAssembler.getCurrentRecipe();
		if (recipe != null) {
			this.renderItemStack(recipe.getOutput(), 95 + this.getGuiLeft(), 42 + this.getGuiTop());
		}*/

		//this.builder.drawLockButton(matrixStack, this, 145, 4, mouseX, mouseY, layer, this.blockEntityLensAssembler.locked);
	}

	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
		if (this.isPointInRect(145, 4, 20, 12, mouseX, mouseY)) {
			//NetworkManager.sendToServer(ServerboundPackets.createPacketLensAssemblerLock(this.blockEntityLensAssembler, !this.blockEntityLensAssembler.locked));
			return true;
		} else {
			return super.mouseClicked(mouseX, mouseY, mouseButton);
		}
	}
}