package tk.dczippl.lasercraft.fabric.network;

import net.minecraft.util.Identifier;
import tk.dczippl.lasercraft.LaserCraft;

public class LaserNetworking {
	public static final Identifier NEW_ITEM_PACKET = LaserCraft.idFrom("new_item");

	/*public static void sendNewItemPacket(World world, BlockPos pos) {
		Stream<PlayerEntity> players = PlayerStream.watching(world, pos);
		players.forEach(player -> ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, NEW_ITEM_PACKET, data));
	}*/
}
