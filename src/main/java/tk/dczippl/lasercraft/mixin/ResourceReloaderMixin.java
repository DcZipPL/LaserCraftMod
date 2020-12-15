package tk.dczippl.lasercraft.mixin;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloadListener;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.util.Unit;
import net.minecraft.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tk.dczippl.lasercraft.fabric.util.ModifiedProgress;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

@Mixin(ResourceReloader.class)
public class ResourceReloaderMixin<S> implements SplashScreenProgressProvider {

	@Mutable
	@Final
	@Shadow
	private Set<ResourceReloadListener> waitingListeners;
	@Mutable
	@Final
	@Shadow
	private int listenerCount;
	@Shadow
	private int applyingCount;
	@Shadow
	private int appliedCount;
	@Final
	@Shadow
	private AtomicInteger preparingCount;
	@Final
	@Shadow
	private AtomicInteger preparedCount;
	@Final
	@Shadow
	protected CompletableFuture<Unit> prepareStageFuture;

	@Shadow @Final protected CompletableFuture<List<S>> applyStageFuture;

	@Inject(at = @At(value = "HEAD"), method = "getProgress()F")
	@Environment(EnvType.CLIENT)
	public void getProgress(CallbackInfoReturnable<Float> ci) {
		//ci.cancel();
		Logger logger = LogManager.getLogger();
		int i = this.listenerCount - this.waitingListeners.size();
		float f = (float)(this.preparedCount.get() * 2 + this.appliedCount * 2 + i * 1);
		float g = (float)(this.preparingCount.get() * 2 + this.applyingCount * 2 + this.listenerCount * 1);
		//logger.info("ResourceLoader: OUT: "+(f / g)+" i: "+i+" f: "+f+" g: "+g);
		if (ModifiedProgress.next!=waitingListeners.size()){
			logger.warn("---NEW---");
			waitingListeners.forEach(resourceReloadListener -> {
				logger.warn(resourceReloadListener.getName());
			});
			logger.warn("---NEW--- >" + f +"|"+g+"|"+i);
			ModifiedProgress.next = waitingListeners.size();
		}
		//ci.setReturnValue((f-6)/(g-6));
	}

	@Inject(at = @At(value = "HEAD"), method = "whenComplete()Ljava/util/concurrent/CompletableFuture;")
	public void whenComplete(CallbackInfoReturnable<CompletableFuture<Unit>> cir) {
		this.applyStageFuture.thenApply((list) -> {Logger logger = LogManager.getLogger();
			logger.warn("Complete: "+list.get(0));
			return Unit.INSTANCE;
		});
	}

	@Override
	public int stichingProgress() {
		return 0;
	}
}
