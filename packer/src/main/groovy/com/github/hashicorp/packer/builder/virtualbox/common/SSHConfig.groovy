/*
 * SSHConfig class
 * Copyright ©  Basil Peace
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
 * file builder/virtualbox/common/ssh_config.go
 * under the terms of the Mozilla Public License, v. 2.0.
 */
package com.github.hashicorp.packer.builder.virtualbox.common

import org.fidata.packer.engine.annotations.ConnectionSetting
import org.fidata.packer.engine.annotations.Inline
import org.fidata.packer.engine.types.base.InterpolableObject
import org.fidata.packer.engine.types.InterpolableUnsignedInteger
import com.github.hashicorp.packer.helper.communicator.CommunicatorConfig
import groovy.transform.CompileStatic

@CompileStatic
abstract class SSHConfig implements InterpolableObject<SSHConfig> {
  @Inline
  abstract CommunicatorConfig getComm()

  @ConnectionSetting
  abstract InterpolableUnsignedInteger getSshHostPortMin()

  @ConnectionSetting
  abstract InterpolableUnsignedInteger getSshHostPortMax()

  @ConnectionSetting
  abstract InterpolableUnsignedInteger getSshSkipNatMapping()
}
