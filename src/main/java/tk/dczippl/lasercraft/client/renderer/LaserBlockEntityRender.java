package tk.dczippl.lasercraft.client.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Matrix4f;
import tk.dczippl.lasercraft.LaserCraft;
import tk.dczippl.lasercraft.fabric.blocks.entities.LaserBlockEntity;
import tk.dczippl.lasercraft.fabric.items.ModItems;

public class LaserBlockEntityRender extends BlockEntityRenderer<LaserBlockEntity> {
	public LaserBlockEntityRender(BlockEntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(LaserBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		matrices.push();
		matrices.translate(0,0,0.51f);
		MinecraftClient.getInstance().getItemRenderer().renderItem(new ItemStack(ModItems.REDSTONE_CRYSTAL), ModelTransformation.Mode.FIXED,light,overlay,matrices,vertexConsumers);

		Sprite sprite = MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).apply(LaserCraft.idFrom("block/part/white_lens_hole"));
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getCutout());
		add(vertexConsumer, matrices, 0, 0, .5f, sprite.getMinU(), sprite.getMinV());
		add(vertexConsumer, matrices, 1, 0, .5f, sprite.getMaxU(), sprite.getMinV());
		add(vertexConsumer, matrices, 1, 1, .5f, sprite.getMaxU(), sprite.getMaxV());
		add(vertexConsumer, matrices, 0, 1, .5f, sprite.getMinU(), sprite.getMaxV());

		matrices.pop();
	}

	private void add(VertexConsumer renderer, MatrixStack stack, float x, float y, float z, float u, float v) {
		renderer.vertex(stack.peek().getModel(),x,y,z)
				.color(1.0f, 1.0f, 1.0f, 1.0f)
				.texture(u, v)
				.light(0, 240)
				.normal(1, 0, 0)
				.next();
	}
}
