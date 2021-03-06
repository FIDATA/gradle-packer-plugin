/*
 * VirtualBoxIso builder
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
 * file builder/virtualbox/iso/builder.go
 * under the terms of the Mozilla Public License, v. 2.0.
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
import org.fidata.packer.engine.AbstractEngine
import org.fidata.packer.engine.BuilderResult
import org.fidata.packer.engine.annotations.AutoImplement
import org.fidata.packer.engine.annotations.Default
import org.fidata.packer.engine.annotations.ExtraProcessed
import org.fidata.packer.engine.annotations.Inline
import org.fidata.packer.engine.types.InterpolableBoolean
import org.fidata.packer.engine.types.InterpolableInteger
import org.fidata.packer.engine.types.InterpolableString
import org.fidata.packer.engine.types.InterpolableUnsignedInteger
import org.fidata.packer.engine.types.InterpolableVBoxGuestAdditionsMode
import com.github.hashicorp.packer.enums.VBoxGuestAdditionsMode
import groovy.transform.CompileStatic
import com.github.hashicorp.packer.template.Builder
import org.fidata.virtualbox.VBoxManageUtils
import org.gradle.api.tasks.Input
import com.google.common.primitives.UnsignedInteger

import static org.fidata.utils.InetAddressUtils.isLocalHost

@AutoImplement(name = 'virtualbox-iso')
@CompileStatic
abstract class VirtualBoxIso extends Builder<VirtualBoxIso> {
  @Inline
  abstract HTTPConfig getHttpConfig()

  @Inline
  abstract ISOConfig getIsoConfig()

  @Inline
  abstract FloppyConfig getFloppyConfig()

  @Inline
  abstract BootConfig getBootConfig()

  @Inline
  abstract ExportConfig getExportConfig()

  @Inline
  abstract ExportOpts getExportOpts()

  @Inline
  abstract OutputConfig getOutputConfig()

  @Inline
  abstract RunConfig getRunConfig()

  @Inline
  abstract ShutdownConfig getShutdownConfig()

  @Inline
  abstract SSHConfig getSshConfig()

  @Inline
  abstract VBoxManageConfig getVboxManageConfig()

  @Inline
  abstract VBoxManagePostConfig getVboxManagePostConfig()

  @Inline
  abstract VBoxVersionConfig getVboxVersionConfig()

  @Input
  @Default({ UnsignedInteger.valueOf(40000L) })
  abstract InterpolableUnsignedInteger getDiskSize()

  @Default({ VBoxGuestAdditionsMode.UPLOAD })
  abstract InterpolableVBoxGuestAdditionsMode getGuestAdditionsMode()

  abstract InterpolableString getGuestAdditionsPath()

  abstract InterpolableString getGuestAdditionsSHA256()

  abstract InterpolableString getGuestAdditionsURL()

  @Input
  @Default({ 'other' })
  abstract InterpolableString getGuestOSType() // TODO: Enum ? (VBoxManage list ostypes)

  @Input
  abstract InterpolableBoolean getHardDriveDiscard()

  @Input
  @Default({ 'ide' })
  abstract InterpolableString getHardDriveInterface() // TODO: Enum

  @Input
  @Default({ 1 })
  abstract InterpolableInteger getSataPortCount()

  @Input
  abstract InterpolableBoolean getHardDriveNonrotational()

  @Input
  @Default({ 'ide' })
  abstract InterpolableString getIsoInterface() // TODO: Enum

  @Input
  @Default({ Boolean.FALSE })
  abstract InterpolableBoolean getKeepRegistered()

  @Input
  @Default({ Boolean.FALSE })
  abstract InterpolableBoolean getSkipExport() // TODO: handle

  @ExtraProcessed // name of the OVF_FILE_EXTENSION file for the new virtual machine, without the file extension
  @Default({ 'packer-{{ .BuildName }}' }) // TODO
  abstract InterpolableString getVmName()

  @Override
  protected final BuilderResult doRun() {
    // TODO
    new BuilderResult(
      ,
      ,
      VBoxManageUtils.getCpusUsed(vboxManageConfig.vboxManage.collect { List<InterpolableString> vboxManageCommand ->
        vboxManageCommand*.interpolated
      })
    )
  }
}
