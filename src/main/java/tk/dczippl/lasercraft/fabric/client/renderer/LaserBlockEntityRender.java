package tk.dczippl.lasercraft.fabric.client.renderer;

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
import tk.dczippl.lasercraft.LaserCraft;
import tk.dczippl.lasercraft.fabric.blocks.entities.LaserBlockEntity;
import tk.dczippl.lasercraft.fabric.items.ModItems;

import static net.minecraft.util.math.Direction.*;

public class LaserBlockEntityRender implements BlockEntityRenderer<LaserBlockEntity> {
	@Override
	public void render(LaserBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		matrices.push();
		float x = 0;
		float y = 0;
		float z = 0;

		Direction direction = entity.getDirection();
		if (direction==NORTH||direction==SOUTH){
			z = 0.501f;
			z *= direction==NORTH ? 1 : -1;
		}
		if (direction==WEST||direction==EAST){
			x = 0.501f;
			x *= direction==WEST ? 1 : -1;
		}
		if (direction==UP||direction==DOWN){
			y = 0.501f;
			y *= direction==UP ? 1 : -1;
		}

		matrices.translate(x, y, z);
		//MinecraftClient.getInstance().getItemRenderer().renderItem(new ItemStack(ModItems.REDSTONE_CRYSTAL), ModelTransformation.Mode.FIXED,light,overlay,matrices,vertexConsumers);

		Sprite sprite = MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).apply(LaserCraft.idFrom("block/part/white_lens_hole"));
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getCutout());

		addFace(sprite,matrices,vertexConsumer, entity.getDirection());
		matrices.pop();

		float[] color = entity.getLensColor();
		float inner = 0.11f + (entity.getStrength()/32f);
		float outer = 0.16f + (entity.getStrength()/32f);
		Direction laserHole = entity.getDirection().getOpposite();
		if (laserHole == SOUTH)
		LaserBeam.renderLightBeam(
				matrices,vertexConsumers,new Identifier("textures/entity/beacon_beam.png"),
				tickDelta,1.0F,entity.getWorld().getTime(),
				0,entity.getRange(), color, inner, outer, 90,0,false
		);
		if (laserHole == NORTH)
		LaserBeam.renderLightBeam(
				matrices,vertexConsumers,new Identifier("textures/entity/beacon_beam.png"),
				tickDelta,1.0F,entity.getWorld().getTime(),
				0,entity.getRange(), color, inner, outer, -90,0,false
		);
		if (laserHole == DOWN)
		LaserBeam.renderLightBeam(
				matrices,vertexConsumers,new Identifier("textures/entity/beacon_beam.png"),
				tickDelta,1.0F,entity.getWorld().getTime(),
				0,entity.getRange(), color, inner, outer, 0,0,false
		);
		if (laserHole == UP)
		LaserBeam.renderLightBeam(
				matrices,vertexConsumers,new Identifier("textures/entity/beacon_beam.png"),
				tickDelta,1.0F,entity.getWorld().getTime(),
				0,entity.getRange(), color, inner, outer, 180,0,false
		);
		if (laserHole == WEST)
		LaserBeam.renderLightBeam(
				matrices,vertexConsumers,new Identifier("textures/entity/beacon_beam.png"),
				tickDelta,1.0F,entity.getWorld().getTime(),
				0,entity.getRange(), color, inner, outer, 0,90,true
		);
		if (laserHole == EAST)
		LaserBeam.renderLightBeam(
				matrices,vertexConsumers,new Identifier("textures/entity/beacon_beam.png"),
				tickDelta,1.0F,entity.getWorld().getTime(),
				0,entity.getRange(), color, inner, outer, 0,-90,true
		);
	}

	private void addFace(Sprite sprite, MatrixStack matrices, VertexConsumer vertexConsumer, Direction direction){
		if (direction== NORTH) {
			addVertex(vertexConsumer, matrices, 0, 0, .5f, sprite.getMinU(), sprite.getMinV());
			addVertex(vertexConsumer, matrices, 1, 0, .5f, sprite.getMaxU(), sprite.getMinV());
			addVertex(vertexConsumer, matrices, 1, 1, .5f, sprite.getMaxU(), sprite.getMaxV());
			addVertex(vertexConsumer, matrices, 0, 1, .5f, sprite.getMinU(), sprite.getMaxV());
		}
		if (direction==SOUTH) {
			addVertex(vertexConsumer, matrices, 0, 0, .5f, sprite.getMinU(), sprite.getMinV());
			addVertex(vertexConsumer, matrices, 0, 1, .5f, sprite.getMaxU(), sprite.getMinV());
			addVertex(vertexConsumer, matrices, 1, 1, .5f, sprite.getMaxU(), sprite.getMaxV());
			addVertex(vertexConsumer, matrices, 1, 0, .5f, sprite.getMinU(), sprite.getMaxV());
		}
		if (direction==EAST) {
			addVertex(vertexConsumer, matrices, .5f, 0, 0, sprite.getMinU(), sprite.getMinV());
			addVertex(vertexConsumer, matrices, .5f, 0, 1, sprite.getMaxU(), sprite.getMinV());
			addVertex(vertexConsumer, matrices, .5f, 1, 1, sprite.getMaxU(), sprite.getMaxV());
			addVertex(vertexConsumer, matrices, .5f, 1, 0, sprite.getMinU(), sprite.getMaxV());
		}
		if (direction==WEST) {
			addVertex(vertexConsumer, matrices, .5f, 0, 0, sprite.getMinU(), sprite.getMinV());
			addVertex(vertexConsumer, matrices, .5f, 1, 0, sprite.getMaxU(), sprite.getMinV());
			addVertex(vertexConsumer, matrices, .5f, 1, 1, sprite.getMaxU(), sprite.getMaxV());
			addVertex(vertexConsumer, matrices, .5f, 0, 1, sprite.getMinU(), sprite.getMaxV());
		}
		if (direction==UP) {
			addVertex(vertexConsumer, matrices, 0,.5f, 0, sprite.getMinU(), sprite.getMinV());
			addVertex(vertexConsumer, matrices, 0,.5f, 1, sprite.getMaxU(), sprite.getMinV());
			addVertex(vertexConsumer, matrices, 1,.5f, 1, sprite.getMaxU(), sprite.getMaxV());
			addVertex(vertexConsumer, matrices, 1,.5f,0, sprite.getMinU(), sprite.getMaxV());
		}
		if (direction==DOWN) {
			addVertex(vertexConsumer, matrices, 0,.5f, 0, sprite.getMinU(), sprite.getMinV());
			addVertex(vertexConsumer, matrices, 1,.5f, 0, sprite.getMaxU(), sprite.getMinV());
			addVertex(vertexConsumer, matrices, 1,.5f, 1, sprite.getMaxU(), sprite.getMaxV());
			addVertex(vertexConsumer, matrices, 0,.5f, 1, sprite.getMinU(), sprite.getMaxV());
		}
	}

	private void addVertex(VertexConsumer renderer, MatrixStack stack, float x, float y, float z, float u, float v) {
		renderer.vertex(stack.peek().getModel(),x,y,z)
				.color(1.0f, 1.0f, 1.0f, 1.0f)
				.texture(u, v)
				.light(0, 240)
				.normal(1, 0, 0)
				.next();
	}

	@Override
	public boolean rendersOutsideBoundingBox(LaserBlockEntity blockEntity) {
		return true;
	}
}