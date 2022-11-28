/*
 * This file is part of the BleachHack distribution (https://github.com/BleachDev/BleachHack/).
 * Copyright (c) 2021 Bleach and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package org.bleachhack.module.mods;

import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;
import org.bleachhack.setting.module.SettingToggle;

public class NoAnnoy extends Module {

	public NoAnnoy() {
		super("NoAnnoy", KEY_UNBOUND, ModuleCategory.HACKERMAN, "Disabled various annoying things on LiveOverflow.",
				new SettingToggle("Demo Screen", true).withDesc("Disabled demo screen on join"),
				new SettingToggle("Game Won", true).withDesc("Disables game won screen"));
	}
}
