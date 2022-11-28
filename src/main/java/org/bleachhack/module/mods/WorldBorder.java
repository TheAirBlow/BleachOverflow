/*
 * This file is part of the BleachHack distribution (https://github.com/BleachDev/BleachHack/).
 * Copyright (c) 2021 Bleach and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package org.bleachhack.module.mods;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorderListener;
import org.bleachhack.event.events.EventEntityControl;
import org.bleachhack.event.events.EventTick;
import org.bleachhack.event.events.EventWorldRender;
import org.bleachhack.eventbus.BleachSubscribe;
import org.bleachhack.mixin.AccessorMinecraftClient;
import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;
import org.bleachhack.setting.module.SettingColor;
import org.bleachhack.setting.module.SettingMode;
import org.bleachhack.setting.module.SettingSlider;
import org.bleachhack.setting.module.SettingToggle;
import org.bleachhack.util.render.Renderer;
import org.bleachhack.util.render.color.QuadColor;

/**
 * @author <a href="https://github.com/lasnikprogram">Lasnik</a>
 */

public class WorldBorder extends Module {
	private double originalBorderSize;

	public WorldBorder() {
		super("WorldBorder", KEY_UNBOUND, ModuleCategory.HACKERMAN, "Makes WorldBorder big and strong for LiveOverflow.");
	}

	@Override
	public void onDisable(boolean inWorld) {
		if (MinecraftClient.getInstance().world != null)
			MinecraftClient.getInstance().world.getWorldBorder().setSize(originalBorderSize);
		super.onDisable(inWorld);
	}

	@BleachSubscribe
	public void onTick(EventTick event) {
		if (MinecraftClient.getInstance().world != null)
			MinecraftClient.getInstance().world.getWorldBorder().setSize(5.9999968E7);
	}

	@Override
	public void onEnable(boolean inWorld) {
		if (MinecraftClient.getInstance().world != null)
			originalBorderSize = MinecraftClient.getInstance().world.getWorldBorder().getSize();
		super.onEnable(inWorld);
	}
}
