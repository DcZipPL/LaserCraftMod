package tk.dczippl.lasercraft.plugin.techreborn;

import techreborn.blocks.GenericMachineBlock;

public class LensAssemblerBlock extends GenericMachineBlock {
	public LensAssemblerBlock() {
		super(ExtendedGuiType.LENS_ASSEMBLER, LensAssemblerBlockEntity::new);
	}
}
