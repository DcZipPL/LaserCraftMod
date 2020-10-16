package tk.dczippl.lasercraft.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.ExplosionEmitterParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class LaserParticle extends Particle {

	private final double endX;
	private final double endY;
	private final double endZ;
	private final float[] color;
	private final double rotationTime;
	private final float size;
	private final float alpha;

	public LaserParticle(ClientWorld world, double startX, double startY, double startZ, double endX, double endY, double endZ, float[] color, int maxAge, double rotationTime, float size, float alpha) {
		super(world, startX, startY, startZ);
		this.endX = endX;
		this.endY = endY;
		this.endZ = endZ;
		this.color = color;
		this.rotationTime = rotationTime;
		this.size = size;
		this.maxAge = maxAge;
		this.alpha = alpha;
	}

	@Override
	public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
		float ageRatio = (float) this.age / (float) this.maxAge;
		float currAlpha = this.alpha - ageRatio * this.alpha;
		renderLaser(this.x + 0.5, this.y + 0.5, this.z + 0.5, this.endX + 0.5, this.endY + 0.5, this.endZ + 0.5, this.rotationTime, currAlpha, this.size, this.color);
	}

	public static final int MAX_LIGHT_X = 0xF000F0;
	public static final int MAX_LIGHT_Y = 0xF000F0;

	//Thanks to feldim2425 for this.
	//I can't do rendering code. Ever.
	@Environment(EnvType.CLIENT)
	public static void renderLaser(double firstX, double firstY, double firstZ, double secondX, double secondY, double secondZ, double rotationTime, float alpha, double beamWidth, float[] color) {
		Tessellator tessy = Tessellator.getInstance();
		BufferBuilder render = tessy.getBuffer();
		World world = MinecraftClient.getInstance().world;

		float r = color[0];
		float g = color[1];
		float b = color[2];

		Vec3d vec1 = new Vec3d(firstX, firstY, firstZ);
		Vec3d vec2 = new Vec3d(secondX, secondY, secondZ);
		Vec3d combinedVec = vec2.subtract(vec1);

		double rot = rotationTime > 0 ? 360D * (world.getTime() % rotationTime / rotationTime) : 0;
		double pitch = Math.atan2(combinedVec.y, Math.sqrt(combinedVec.x * combinedVec.x + combinedVec.z * combinedVec.z));
		double yaw = Math.atan2(-combinedVec.z, combinedVec.x);

		double length = combinedVec.length();

		GlStateManager.pushMatrix();

		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA.field_22545, GlStateManager.DstFactor.ONE.field_22528);
		int func = GL11.glGetInteger(GL11.GL_ALPHA_TEST_FUNC);
		float ref = GL11.glGetFloat(GL11.GL_ALPHA_TEST_REF);
		GlStateManager.alphaFunc(GL11.GL_ALWAYS, 0);
		GlStateManager.translatef((float)firstX/* - BlockEntityRenderDispatcher.staticPlayerX*/, (float)firstY/* - TileEntityRendererDispatcher.staticPlayerY*/, (float)firstZ/* - TileEntityRendererDispatcher.staticPlayerZ*/);
		GlStateManager.rotatef((float) (180 * yaw / Math.PI), 0, 1, 0);
		GlStateManager.rotatef((float) (180 * pitch / Math.PI), 0, 0, 1);
		GlStateManager.rotatef((float) rot, 1, 0, 0);

		GlStateManager.disableTexture();//2d
		render.begin(GL11.GL_QUADS, VertexFormats.POSITION_COLOR_TEXTURE_LIGHT);//POSITION_TEX_LMAP_COLOR
		for (double i = 0; i < 4; i++) {
			double width = beamWidth * (i / 4.0);
			render.vertex(length, width, width).texture(0, 0).light(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).next();
			render.vertex(0, width, width).texture(0, 0).light(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).next();
			render.vertex(0, -width, width).texture(0, 0).light(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).next();
			render.vertex(length, -width, width).texture(0, 0).light(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).next();

			render.vertex(length, -width, -width).texture(0, 0).light(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).next();
			render.vertex(0, -width, -width).texture(0, 0).light(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).next();
			render.vertex(0, width, -width).texture(0, 0).light(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).next();
			render.vertex(length, width, -width).texture(0, 0).light(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).next();

			render.vertex(length, width, -width).texture(0, 0).light(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).next();
			render.vertex(0, width, -width).texture(0, 0).light(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).next();
			render.vertex(0, width, width).texture(0, 0).light(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).next();
			render.vertex(length, width, width).texture(0, 0).light(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).next();

			render.vertex(length, -width, width).texture(0, 0).light(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).next();
			render.vertex(0, -width, width).texture(0, 0).light(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).next();
			render.vertex(0, -width, -width).texture(0, 0).light(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).next();
			render.vertex(length, -width, -width).texture(0, 0).light(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).next();
		}
		tessy.draw();

		GlStateManager.enableTexture();
		//}

		GlStateManager.alphaFunc(func, ref);
		GlStateManager.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA.field_22545, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA.field_22528);
		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}


	@Override
	public ParticleTextureSheet getType() {
		return null;
	}
}
