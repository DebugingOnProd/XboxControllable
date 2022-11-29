package org.lhq.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import org.lhq.XboxControllable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class XboxMixin {

    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        XboxControllable.LOGGER.info("mixin");
    }
}
