package tk.dczippl.lasercraft.network;

import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.fabric.api.server.PlayerStream;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tk.dczippl.lasercraft.LaserCraft;

import java.util.stream.Stream;

public class LaserNetworking {
	public static final Identifier NEW_ITEM_PACKET = LaserCraft.idFrom("new_item");

	/*public static void sendNewItemPacket(World world, BlockPos pos) {
		Stream<PlayerEntity> players = PlayerStream.watching(world, pos);
		players.forEach(player -> ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, NEW_ITEM_PACKET, data));
	}*/
}
