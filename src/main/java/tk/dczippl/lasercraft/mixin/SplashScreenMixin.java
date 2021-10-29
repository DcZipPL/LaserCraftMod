package tk.dczippl.lasercraft.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SplashOverlay.class)
public class SplashScreenMixin {
	@Shadow
	private float progress;

	@Final
	@Shadow
	private net.minecraft.resource.ResourceReload reload;

	@Inject(at = @At(value = "HEAD"), method = "renderProgressBar(Lnet/minecraft/client/util/math/MatrixStack;IIIIF)V")
	private void renderProgressBar(MatrixStack matrixStack, int i, int j, int k, int l, float f, CallbackInfo ci) {
		//float progressMod = ModifiedProgress.getProgress();
		float u = this.reload.getProgress();
		float r = 0f;//u >= 0.95f ? 0.0f : 0.2f;
		Logger logger = LogManager.getLogger();
		//logger.info("U: "+progress+" W: "+u+" P: "+reloadMonitor.isPrepareStageComplete()+" W: "+u);
		progress = MathHelper.clamp((this.progress - r) * 0.93f + u * 0.050000012F, 0.0F, 1.0F);
	}

	@Inject(at = @At(value = "TAIL"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V")
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		int i = MinecraftClient.getInstance().getWindow().getScaledWidth();
		double d = Math.min((double)MinecraftClient.getInstance().getWindow().getScaledWidth() * 0.75D, (double)MinecraftClient.getInstance().getWindow().getScaledHeight()) * 0.25D;
		double e = d * 4.0D;
		MinecraftClient.getInstance().textRenderer.draw(matrices, Math.round(reload.getProgress() * 10000f) / 100f +"%",i / 2 - 10, 10,0xFFFFFF);
	}
}