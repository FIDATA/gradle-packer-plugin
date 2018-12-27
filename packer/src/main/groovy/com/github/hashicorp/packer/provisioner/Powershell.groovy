/*
 * Powershell class
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
package com.github.hashicorp.packer.provisioner

import com.fasterxml.jackson.annotation.JsonProperty
import org.fidata.packer.engine.types.InterpolableDuration
import org.fidata.packer.engine.types.InterpolableFile
import org.fidata.packer.engine.types.InterpolableInteger
import groovy.transform.CompileStatic
import com.github.hashicorp.packer.template.Provisioner
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Optional

@CompileStatic
class Powershell extends Provisioner<Configuration> {
  static class Configuration extends Provisioner.Configuration {
    @Internal
    Boolean binary

    @Input
    @Optional
    List<String> inline

    @InputFile
    @Optional
    InterpolableFile script

    @InputFiles
    @Optional
    List<InterpolableFile> scripts

    @JsonProperty('environment_vars')
    @Input
    @Optional
    List<String> vars

    @Internal
    String remotePath

    @Internal
    String remoteEnvVarPath

    @Input // TODO
    String executeCommand

    @Input // TODO
    String elevatedExecuteCommand

    @Internal
    InterpolableDuration startRetryTimeout

    @Internal
    String elevatedEnvVarFormat

    @Input
    String elevatedUser

    @Internal
    String elevatedPassword

    @Internal
    List<InterpolableInteger> validExitCodes
  }
}
