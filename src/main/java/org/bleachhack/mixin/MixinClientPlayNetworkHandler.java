package org.bleachhack.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import org.bleachhack.module.ModuleManager;
import org.bleachhack.module.mods.NoAnnoy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler {

    @Inject(method = { "onGameStateChange" }, at = @At("HEAD"), cancellable = true)
    private void onGameStateChange(GameStateChangeS2CPacket packet, CallbackInfo ci) {
        var module = ModuleManager.getModule(NoAnnoy.class);
        if (module.isEnabled()) {
            var reason = packet.getReason();
            if (reason == GameStateChangeS2CPacket.DEMO_MESSAGE_SHOWN
                    && module.getSetting(0).asToggle().getState())
                ci.cancel();
            else if (reason == GameStateChangeS2CPacket.GAME_WON
                        && module.getSetting(1).asToggle().getState())
                ci.cancel();
        }
    }
}
