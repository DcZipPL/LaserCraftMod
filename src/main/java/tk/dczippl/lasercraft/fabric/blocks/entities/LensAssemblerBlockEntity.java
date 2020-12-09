package tk.dczippl.lasercraft.fabric.blocks.entities;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.util.math.Direction;
import reborncore.api.IToolDrop;
import reborncore.api.blockentity.InventoryProvider;
import reborncore.client.screen.BuiltScreenHandlerProvider;
import reborncore.client.screen.builder.BuiltScreenHandler;
import reborncore.client.screen.builder.ScreenHandlerBuilder;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;
import reborncore.common.util.RebornInventory;
import team.reborn.energy.EnergySide;
import techreborn.blockentity.machine.tier1.AutoCraftingTableBlockEntity;
import tk.dczippl.lasercraft.fabric.blocks.ModBlocks;

public class LensAssemblerBlockEntity extends PowerAcceptorBlockEntity implements IToolDrop, InventoryProvider, BuiltScreenHandlerProvider {
	public RebornInventory<AutoCraftingTableBlockEntity> inventory = new RebornInventory(11, "LensAssemblerBlockEntity", 64, this);
	private final int OUTPUT_SLOT = 9;
	private final int EXTRA_OUTPUT_SLOT = 10;
	public int progress;
	public int maxProgress = 120;
	public int euTick = 10;
	public int balanceSlot = 0;

	public LensAssemblerBlockEntity() {
		super(ModBlockEntities.LENS_ASSEMBLER);
	}

	@Override
	public double getBaseMaxPower() {
		return 10000;
	}

	@Override
	public double getBaseMaxOutput() {
		return 0.0D;
	}

	@Override
	public double getBaseMaxInput() {
		return 32;
	}

	@Override
	public ItemStack getToolDrop(PlayerEntity playerEntity) {
		return new ItemStack(ModBlocks.LENS_ASSEMBLER);
	}

	@Override
	public Inventory getInventory() {
		return this.inventory;
	}

	@Override
	public BuiltScreenHandler createScreenHandler(int syncID, PlayerEntity player) {
		return (new ScreenHandlerBuilder("lensassembler")).player(player.inventory)
				.inventory().hotbar().addInventory().blockEntity(this)
				.slot(0, 28, 25)
				.slot(1, 46, 25)
				.slot(2, 64, 25)
				.slot(3, 28, 43)
				.slot(4, 46, 43)
				.slot(5, 64, 43)
				.slot(6, 28, 61)
				.slot(7, 46, 61)
				.slot(8, 64, 61)
				.outputSlot(9, 145, 42)
				.outputSlot(10, 145, 70)
				.syncEnergyValue().sync(this::getProgress, this::setProgress).sync(this::getMaxProgress, this::setMaxProgress)
				.addInventory().create(this, syncID);
	}

	public int getMaxProgress() {
		return 0;
	}

	public void setMaxProgress(int progress) {
	}

	public int getProgress() {
		return 0;
	}

	public void setProgress(int progress) {
	}

	@Override
	protected boolean canProvideEnergy(EnergySide side) {
		return false;
	}

	public boolean canBeUpgraded() {
		return false;
	}

	public boolean hasSlotConfig() {
		return true;
	}

	public Direction getFacingEnum() {
		return Direction.NORTH;
	}
}