package tk.dczippl.lasercraft.fabric.init;

import dev.onyxstudios.cca.api.v3.chunk.ChunkComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.chunk.ChunkComponentInitializer;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import net.minecraft.util.Identifier;
import tk.dczippl.lasercraft.fabric.util.BreakData;

public class ModComponent implements ChunkComponentInitializer {
	public static final ComponentKey<BreakData.BreakDataComponent> BREAKDATA =
			ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier("lasercraft:breakdata"), BreakData.BreakDataComponent.class);

	@Override
	public void registerChunkComponentFactories(ChunkComponentFactoryRegistry registry) {
		registry.register(BREAKDATA,chunk -> new BreakData.BreakDataComponent());
	}
}