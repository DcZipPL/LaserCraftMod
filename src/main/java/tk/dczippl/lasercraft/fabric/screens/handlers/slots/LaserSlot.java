package tk.dczippl.lasercraft.fabric.screens.handlers.slots;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class LaserSlot extends Slot {
	private SlotType slotType;

	public LaserSlot(SlotType slotType, Inventory inventory, int i, int x, int y) {
		super(inventory,i,x,y);
		this.slotType = slotType;
	}

	@Override
	public boolean canInsert(ItemStack stack) {
		return slotType == SlotType.LENS
				? stack.getItem() instanceof LensItem && super.canInsert(stack)
				: stack.getItem() instanceof CoilItem && super.canInsert(stack);
	}

	enum SlotType{
		LENS,
		COIL
	}
}
