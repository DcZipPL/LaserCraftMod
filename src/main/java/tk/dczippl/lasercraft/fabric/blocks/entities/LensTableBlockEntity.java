package tk.dczippl.lasercraft.fabric.blocks.entities;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import tk.dczippl.lasercraft.fabric.init.ModTags;
import tk.dczippl.lasercraft.fabric.items.ModItems;
import tk.dczippl.lasercraft.fabric.screens.handlers.LensTableScreenHandler;
import tk.dczippl.lasercraft.fabric.util.ImplementedInventory;
import tk.dczippl.lasercraft.fabric.util.LensValues;

public class LensTableBlockEntity  extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory, Tickable {
	private int invsize = 6;
	private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(invsize, ItemStack.EMPTY);

	public LensTableBlockEntity() {
		super(ModBlockEntities.LENS_TABLE);
	}

	//These Methods are from the NamedScreenHandlerFactory Interface
	//createMenu creates the ScreenHandler itself
	//getDisplayName will Provide its name which is normally shown at the top

	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
		//We provide *this* to the screenHandler as our class Implements Inventory
		//Only the Server has the Inventory at the start, this will be synced to the client in the ScreenHandler
		return new LensTableScreenHandler(syncId, playerInventory, this);
	}

	@Override
	public Text getDisplayName() {
		return new TranslatableText(getCachedState().getBlock().getTranslationKey());
	}

	@Override
	public void fromTag(BlockState state, CompoundTag tag) {
		super.fromTag(state, tag);
		inventory = DefaultedList.ofSize(invsize, ItemStack.EMPTY);
		Inventories.fromTag(tag, this.inventory);
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		super.toTag(tag);
		Inventories.toTag(tag, this.inventory);
		return tag;
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}

	@Override
	public void onInvUpdate() {

	}

	@Override
	public boolean canPlayerUse(PlayerEntity player) {
		return true;
	}

	@Override
	public void tick() {
		if (ModTags.LENS_BORDER.contains(inventory.get(0).getItem())){
			if (ModTags.LENS_GLASS.contains(inventory.get(3).getItem())){
				ItemStack lens = new ItemStack(ModItems.LENS);
				lens.getOrCreateTag().putInt("color",getColor());
				lens.getTag().putInt("strength",getStrength());
				lens.getTag().putInt("range",getRange());
				inventory.set(6,lens);
			}
		}
	}

	int[] modifierSlots = {1,2,4,5};

	private int getRange() {
		if (ModTags.LENS_GLASS.contains(inventory.get(3).getItem())){
			//return LensValues.getGlassFromJson();
		}
		return 0;
	}

	private int getStrength() {
		for (int i : modifierSlots){
			if (ModTags.LENS_MODIFIER.contains(inventory.get(i).getItem())){

			}
		}
		return 0;
	}

	private int getColor() {
		for (int i : modifierSlots){
			if (ModTags.LENS_MODIFIER.contains(inventory.get(i).getItem())){

			}
		}
		return 0;
	}
}