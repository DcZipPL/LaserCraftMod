package tk.dczippl.lasercraft.plugin.techreborn;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import reborncore.RebornCore;
import reborncore.api.blockentity.IMachineGuiHandler;
import reborncore.client.screen.BuiltScreenHandlerProvider;
import reborncore.client.screen.builder.BuiltScreenHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ExtendedGuiType<T extends BlockEntity> implements IMachineGuiHandler {
	private static final Map<Identifier, ExtendedGuiType<?>> TYPES = new HashMap();
	private final Identifier identifier;
	private final Supplier<Supplier<ExtendedGuiType.GuiFactory<T>>> guiFactory;
	private final ScreenHandlerType<BuiltScreenHandler> screenHandlerType;

	public static final ExtendedGuiType<LensAssemblerBlockEntity> LENS_ASSEMBLER = register("lens_assembler", () -> {
		return () -> {
			return LensAssemblerScreen::new;
		};
	});

	private ExtendedGuiType(Identifier identifier, Supplier<Supplier<ExtendedGuiType.GuiFactory<T>>> factorySupplierMeme) {
		this.identifier = identifier;
		this.guiFactory = factorySupplierMeme;
		this.screenHandlerType = ScreenHandlerRegistry.registerExtended(identifier, this.getScreenHandlerFactory());
		RebornCore.clientOnly(() -> {
			return () -> {
				ScreenRegistry.register(this.screenHandlerType, this.getGuiFactory());
			};
		});
	}

	@Environment(EnvType.CLIENT)
	private ExtendedGuiType.GuiFactory<T> getGuiFactory() {
		return (ExtendedGuiType.GuiFactory)((Supplier)this.guiFactory.get()).get();
	}

	@Override
	public void open(PlayerEntity player, BlockPos pos, World world) {
		if (!world.isClient) {
			player.openHandledScreen(new ExtendedScreenHandlerFactory() {
				public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
					packetByteBuf.writeBlockPos(pos);
				}

				public Text getDisplayName() {
					return new LiteralText("What is this for?");
				}

				@Nullable
				public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
					BlockEntity blockEntity = player.world.getBlockEntity(pos);
					BuiltScreenHandler screenHandler = ((BuiltScreenHandlerProvider)blockEntity).createScreenHandler(syncId, player);
					screenHandler.setType(ExtendedGuiType.this.screenHandlerType);
					return screenHandler;
				}
			});
		}
	}

	private static <T extends BlockEntity> ExtendedGuiType<T> register(String id, Supplier<Supplier<ExtendedGuiType.GuiFactory<T>>> factorySupplierMeme) {
		return register(new Identifier("techreborn", id), factorySupplierMeme);
	}

	public static <T extends BlockEntity> ExtendedGuiType<T> register(Identifier identifier, Supplier<Supplier<ExtendedGuiType.GuiFactory<T>>> factorySupplierMeme) {
		if (TYPES.containsKey(identifier)) {
			throw new RuntimeException("Duplicate gui type found");
		} else {
			ExtendedGuiType<T> type = new ExtendedGuiType(identifier, factorySupplierMeme);
			TYPES.put(identifier, type);
			return type;
		}
	}

	private ScreenHandlerRegistry.ExtendedClientHandlerFactory<BuiltScreenHandler> getScreenHandlerFactory() {
		return (syncId, playerInventory, packetByteBuf) -> {
			BlockEntity blockEntity = playerInventory.player.world.getBlockEntity(packetByteBuf.readBlockPos());
			BuiltScreenHandler screenHandler = ((BuiltScreenHandlerProvider)blockEntity).createScreenHandler(syncId, playerInventory.player);
			screenHandler.setType(this.screenHandlerType);
			return screenHandler;
		};
	}

	@Environment(EnvType.CLIENT)
	public interface GuiFactory<T extends BlockEntity> extends ScreenRegistry.Factory<BuiltScreenHandler, HandledScreen<BuiltScreenHandler>> {
		HandledScreen<?> create(int var1, PlayerEntity var2, T var3);

		default HandledScreen create(BuiltScreenHandler builtScreenHandler, PlayerInventory playerInventory, Text text) {
			PlayerEntity playerEntity = playerInventory.player;
			T blockEntity = (T) builtScreenHandler.getBlockEntity();
			return this.create(builtScreenHandler.syncId, playerEntity, blockEntity);
		}
	}
}
