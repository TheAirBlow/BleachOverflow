/*
 * This file is part of the BleachHack distribution (https://github.com/BleachDev/BleachHack/).
 * Copyright (c) 2021 Bleach and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package org.bleachhack.module.mods;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Vec3d;
import org.bleachhack.event.events.EventClientMove;
import org.bleachhack.event.events.EventSendMovementPackets;
import org.bleachhack.event.events.EventTick;
import org.bleachhack.eventbus.BleachSubscribe;
import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;
import org.bleachhack.setting.module.SettingSlider;

public class SlowMove extends Module {
	private Vec3d lastPosition;

	public SlowMove() {
		super("BadGuard", KEY_UNBOUND, ModuleCategory.HACKERMAN, "Magically walk into WorldGuard areas on LiveOverflow.",
				new SettingSlider("Speed", 0, 5, 0.1, 9).withDesc("Movement speed"));
	}

	@BleachSubscribe
	public void onTick(EventTick event) {
		var speed = getSetting(0).asSlider().getValueFloat();
		if (mc.player != null) {
			float forward = mc.player.forwardSpeed;
float side = mc.player.sidewaysSpeed;
float yaw = mc.player.prevYaw + (mc.player.getYaw() - mc.player.prevYaw);

if (forward != 0.0F) {
        if (side > 0.0F) {
                yaw += ((forward > 0.0F) ? -45 : 45);
        } else if (side < 0.0F) {
                yaw += ((forward > 0.0F) ? 45 : -45);
        }

        side = 0.0F;

        if (forward > 0.0F) {
                forward = 1.0F;
        } else if (forward < 0.0F) {
                forward = -1.0F;
        }
}

double sin = Math.sin(Math.toRadians(yaw + 90.0F));
double cos = Math.cos(Math.toRadians(yaw + 90.0F));
double dx = forward * speed * cos + side * speed * sin;
double dz = forward * speed * sin - side * speed * cos;
double dy = 0;

			if (mc.player.input.jumping)
				dy = speed;

			if (mc.player.input.sneaking)
				dy = -speed;

			if (dy != 0 || dz != 0 || dx != 0) {
				var realY = mc.player.getY() + dy;
				var realX = mc.player.getX() + dx;
				var realZ = mc.player.getX() + dz;
				mc.player.updatePosition(realX, realY, realZ);
				mc.player.updatePosition(realX, -5, realZ);
			}
		}
	}
}
