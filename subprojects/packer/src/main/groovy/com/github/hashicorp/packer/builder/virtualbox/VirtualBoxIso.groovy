/*
 * VirtualBoxIso class
 * Copyright © 2018  Basil Peace
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
 */
package com.github.hashicorp.packer.builder.virtualbox

import com.github.hashicorp.packer.builder.virtualbox.common.ExportConfig
import com.github.hashicorp.packer.builder.virtualbox.common.ExportOpts
import com.github.hashicorp.packer.builder.virtualbox.common.OutputConfig
import com.github.hashicorp.packer.builder.virtualbox.common.RunConfig
import com.github.hashicorp.packer.builder.virtualbox.common.SSHConfig
import com.github.hashicorp.packer.builder.virtualbox.common.ShutdownConfig
import com.github.hashicorp.packer.builder.virtualbox.common.VBoxManageConfig
import com.github.hashicorp.packer.builder.virtualbox.common.VBoxManagePostConfig
import com.github.hashicorp.packer.builder.virtualbox.common.VBoxVersionConfig
import com.github.hashicorp.packer.common.FloppyConfig
import com.github.hashicorp.packer.common.HTTPConfig
import com.github.hashicorp.packer.common.ISOConfig
import com.github.hashicorp.packer.common.bootcommand.BootConfig
import com.github.hashicorp.packer.engine.annotations.Inline
import com.github.hashicorp.packer.engine.types.InterpolableBoolean
import com.github.hashicorp.packer.engine.types.InterpolableInteger
import com.github.hashicorp.packer.engine.types.InterpolableString
import com.github.hashicorp.packer.engine.types.InterpolableUnsignedInteger
import com.github.hashicorp.packer.engine.types.InterpolableVBoxGuestAdditionsMode
import groovy.transform.AutoClone
import groovy.transform.AutoCloneStyle
import groovy.transform.CompileStatic
import com.github.hashicorp.packer.template.Builder
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal

@AutoClone(style = AutoCloneStyle.SIMPLE)
@CompileStatic
class VirtualBoxIso extends Builder {
  @Inline
  HTTPConfig httpConfig

  @Inline
  ISOConfig isoConfig

  @Inline
  FloppyConfig floppyConfig

  @Inline
  BootConfig bootConfig

  @Inline
  ExportConfig exportConfig

  @Inline
  ExportOpts exportOpts

  @Inline
  OutputConfig outputConfig

  @Inline
  RunConfig runConfig

  @Inline
  ShutdownConfig shutdownConfig

  @Inline
  SSHConfig sshConfig

  @Inline
  VBoxManageConfig vboxManageConfig

  @Inline
  VBoxManagePostConfig vboxManagePostConfig

  @Inline
  VBoxVersionConfig vboxVersionConfig

  @Input
  // @Default(value = '40000')
  InterpolableUnsignedInteger diskSize

  // @Default(value = 'upload')
  InterpolableVBoxGuestAdditionsMode guestAdditionsMode

  InterpolableString guestAdditionsPath

  InterpolableString guestAdditionsSHA256

  InterpolableString guestAdditionsURL

  @Input
  // @Default(value = 'other')
  InterpolableString guestOSType // TODO: Enum ? (VBoxManage list ostypes)

  @Input
  InterpolableBoolean hardDriveDiscard

  @Input
  // @Default(value = 'ide')
  InterpolableString hardDriveInterface // TODO: Enum

  @Input
  // @Default(value = '1')
  InterpolableInteger sataPortCount

  @Input
  InterpolableBoolean hardDriveNonrotational

  // @Default(value = 'ide')
  InterpolableString isoInterface // TODO: Enum

  @Input
  // @Default(value = 'false')
  InterpolableBoolean keepRegistered

  @Input
  // @Default(value = 'false')
  InterpolableBoolean skipExport // TODO: handle

  @Internal // name of the OVF file for the new virtual machine, without the file extension
  // @Default(value = 'packer-BUILDNAME') // TODO
  InterpolableString vmName
}
