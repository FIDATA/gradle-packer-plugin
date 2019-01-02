/*
 * RunConfig class
 * Copyright © 2018-2019  Basil Peace
 *
 * This file is part of gradle-packer-plugin.
 *
 * This plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this plugin.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Ported from original Packer code,
 * file builder/virtualbox/common/run_config.go
 * under the terms of the Mozilla Public License, v. 2.0.
 */
package com.github.hashicorp.packer.builder.virtualbox.common

import org.fidata.packer.engine.annotations.AutoImplement
import org.fidata.packer.engine.annotations.LaunchedVMConfiguration
import org.fidata.packer.engine.types.InterpolableBoolean
import org.fidata.packer.engine.types.base.InterpolableObject
import org.fidata.packer.engine.types.InterpolableString
import org.fidata.packer.engine.types.InterpolableUnsignedInteger
import groovy.transform.CompileStatic

@AutoImplement
@CompileStatic
abstract class RunConfig implements InterpolableObject<RunConfig> {
  @LaunchedVMConfiguration // TOTEST
  abstract InterpolableBoolean getHeadless()

  @LaunchedVMConfiguration // TOTEST
  abstract InterpolableString getVrdpBindAddress()

  @LaunchedVMConfiguration // TOTEST
  abstract InterpolableUnsignedInteger getVrdpPortMin()

  @LaunchedVMConfiguration // TOTEST
  abstract InterpolableUnsignedInteger getVrdpPortMax()
}
