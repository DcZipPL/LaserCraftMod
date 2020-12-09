package tk.dczippl.lasercraft.fabric.blocks;

import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;
import tk.dczippl.lasercraft.fabric.blocks.entities.LensAssemblerBlockEntity;

public class LensAssemblerBlock extends BlockWithEntity {
	public LensAssemblerBlock() {
		super(Settings.of(Material.METAL));
	}

	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new LensAssemblerBlockEntity();
	}
}
