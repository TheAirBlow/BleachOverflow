/*
 * This file is part of the BleachHack distribution (https://github.com/BleachDev/BleachHack/).
 * Copyright (c) 2021 Bleach and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package org.bleachhack.module.mods;

import org.bleachhack.event.events.EventTick;
import org.bleachhack.eventbus.BleachSubscribe;
import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;

public class GameMode extends Module {
	private net.minecraft.world.GameMode lastGameMode;

	public GameMode() {
		super("GameMode", KEY_UNBOUND, ModuleCategory.HACKERMAN, "Disables fake GameMode on LiveOverflow.");
	}

	@BleachSubscribe
	public void onTick(EventTick event) {
		mc.interactionManager.setGameMode(net.minecraft.world.GameMode.SURVIVAL);
	}
}
