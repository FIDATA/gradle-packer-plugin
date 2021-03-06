/*
 * Shell provisioner
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
 * file provisioner/shell/provisioner.go
 * under the terms of the Mozilla Public License, v. 2.0.
 */
package com.github.hashicorp.packer.provisioner

import com.fasterxml.jackson.annotation.JsonProperty
import org.fidata.packer.engine.AbstractEngine
import org.fidata.packer.engine.annotations.AutoImplement
import org.fidata.packer.engine.annotations.Timing
import org.fidata.packer.engine.annotations.Staging
import org.fidata.packer.engine.types.InterpolableDuration
import org.fidata.packer.engine.types.InterpolableFile
import groovy.transform.CompileStatic
import com.github.hashicorp.packer.template.Provisioner
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity

@CompileStatic
class Shell extends Provisioner<Config> {
  @AutoImplement
  abstract static class Config extends Provisioner.Config<Config> {
    @Internal
    abstract Boolean getBinary()

    @Input
    @Optional
    abstract List<String> getInline()

    @Input // TODO
    abstract String getInlineShebang()

    @InputFile
    @PathSensitive(PathSensitivity.NONE) // TODOC
    @Optional
    abstract InterpolableFile getScript()

    @InputFiles
    @PathSensitive(PathSensitivity.NONE) // TODOC
    @Optional
    abstract List<InterpolableFile> getScripts()

    @JsonProperty('environment_vars')
    @Input
    @Optional
    abstract List<String> getVars()

    @Staging
    abstract String getRemoteEnvVarPath()

    @Staging
    abstract String getRemoteFolder()

    @Staging
    abstract String getRemoteFile()

    // TODO: defaults to remote_folder/remote_file
    @Staging
    abstract String getRemotePath()

    @Input // TODO
    abstract List<String> getExecuteCommand()

    @Timing
    abstract InterpolableDuration getStartRetryTimeout()

    @Input
    abstract Boolean getSkipClean()

    @Timing
    abstract Boolean getExpectDisconnect()
  }

  static void register(AbstractEngine engine) {
    engine.registerSubtype Provisioner, 'shell', this
  }
}
