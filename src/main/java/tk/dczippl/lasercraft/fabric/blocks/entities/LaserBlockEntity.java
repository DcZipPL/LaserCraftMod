package tk.dczippl.lasercraft.fabric.blocks.entities;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tk.dczippl.lasercraft.fabric.blocks.LaserBlock;
import tk.dczippl.lasercraft.fabric.items.LensItem;
import tk.dczippl.lasercraft.fabric.screens.handlers.LaserScreenHandler;
import tk.dczippl.lasercraft.fabric.util.BreakData;
import tk.dczippl.lasercraft.fabric.util.ImplementedInventory;
import tk.dczippl.lasercraft.fabric.util.LaserColor;

import java.util.List;

public class LaserBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory, Tickable, BlockEntityClientSerializable {
	private int invsize = 7;
	private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(invsize, ItemStack.EMPTY);

	public LaserBlockEntity() {
		super(ModBlockEntities.LASER);
	}

	//These Methods are from the NamedScreenHandlerFactory Interface
	//createMenu creates the ScreenHandler itself
	//getDisplayName will Provide its name which is normally shown at the top

	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
		//We provide *this* to the screenHandler as our class Implements Inventory
		//Only the Server has the Inventory at the start, this will be synced to the client in the ScreenHandler
		return new LaserScreenHandler(syncId, playerInventory, this);
	}

	@Override
	public Text getDisplayName() {
		return new TranslatableText(getCachedState().getBlock().getTranslationKey());
	}

	@Override
	public void fromTag(BlockState state, CompoundTag tag) {
		super.fromTag(state, tag);
		fromClientTag(tag);
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		super.toTag(tag);
		toClientTag(tag);
		return tag;
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}

	@Override
	public void onInvUpdate() {
		if (!world.isClient)
			sync();
		this.markDirty();
		sendUpdate();
	}

	@Override
	public void sync() {
		markDirty();
		BlockEntityClientSerializable.super.sync();
	}

	@Override
	public boolean canPlayerUse(PlayerEntity player) {
		return true;
	}

	public Direction getDirection() {
		return world.getBlockState(pos).get(LaserBlock.FACING);
	}

	public int getStrength(){
		ItemStack lens = inventory.get(0);
		if (lens.getItem() instanceof LensItem)
			if (lens.getOrCreateTag().contains("strength"))
				return lens.getTag().getInt("strength");
		return 0; //lensStrength
	}

	public int getRange(){
		ItemStack lens = inventory.get(0);
		if (lens.getItem() instanceof LensItem)
			if (lens.getOrCreateTag().contains("range"))
				return lens.getTag().getInt("range");
		return 8; //lensRange
	}

	public float[] getLensColor() {
		ItemStack lens = inventory.get(0);
		if (lens.getItem() instanceof LensItem)
			if (lens.getOrCreateTag().contains("color"))
				switch (lens.getTag().getInt("color")){
					case 0:
						return new float[]{1f, 1f, 1f};
					case 1:
						return new float[]{1f, 0f, 0f};
					case 2:
						return new float[]{0f, 0f, 1f};
					case 3:
						return new float[]{0f, 1f, 1f};
					case 4:
						return new float[]{0f, 1f, 0f};
					case 5:
						return new float[]{1f, 0f, 1f};
					case 6:
						return new float[]{1f, 1f, 0f};
					default:
						return new float[]{0f, 0f, 0f};
				}
		return new float[]{1f, 1f, 1f};
	}

	@Override
	public void tick() {
		ItemStack lens = inventory.get(0);
		if (lens.getItem() instanceof LensItem)
			if (lens.getOrCreateTag().contains("color")){
				int color = lens.getTag().getInt("color");
				if (color == LaserColor.RED.ordinal()){
					List<Entity> entities = world.getNonSpectatingEntities(
							Entity.class,
							new Box(
									pos.add(1.5f,1.5f,1.5f),
									pos.offset(getDirection().getOpposite(),getRange()).add(0.5f,0.5f,0.5f)
							)
						);
					entities.forEach(entity -> {
						entity.damage(DamageSource.IN_FIRE,getStrength()*0.5f+0.5f);
					});
					BlockPos.iterate(pos.offset(getDirection().getOpposite()), pos.offset(getDirection().getOpposite(),getRange())).forEach(blockPos -> {

						BreakData.getBlockBreaker(world).breakBlock(blockPos);
						//world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
					});
				}
				if (color == LaserColor.GREEN.ordinal()){
					BlockPos.iterate(pos.offset(getDirection().getOpposite()), pos.offset(getDirection().getOpposite(),getRange())).forEach(blockPos -> {
						if (world.getBlockState(blockPos).getBlock() instanceof CropBlock){
							((CropBlock)world.getBlockState(blockPos).getBlock()).grow((ServerWorld) world,world.random,blockPos,world.getBlockState(blockPos));
						}
					});
				}
			}
	}

	@Override
	public void markDirty() {
		super.markDirty();
		sendUpdate();
	}

	private void sendUpdate() {
		if (this.world != null) {
			BlockState state = this.world.getBlockState(this.pos);
			(this.world).updateListeners(this.pos, state, state, 3);
		}
	}

	@Override
	public void fromClientTag(CompoundTag compoundTag) {
		this.inventory.clear();
		inventory = DefaultedList.ofSize(invsize, ItemStack.EMPTY);
		Inventories.fromTag(compoundTag, this.inventory);
	}

	@Override
	public CompoundTag toClientTag(CompoundTag compoundTag) {
		Inventories.toTag(compoundTag, this.inventory);
		return compoundTag;
	}
}