/*
 * PackerExecSpec class
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
 */
package org.fidata.gradle.packer

import groovy.transform.CompileStatic
import org.gradle.api.Project
import org.ysb33r.grolifant.api.exec.AbstractCommandExecSpec
import org.ysb33r.grolifant.api.exec.ExternalExecutable

@CompileStatic
class PackerExecSpec extends AbstractCommandExecSpec {
  /*
   * WORKAROUND:
   * Without setter there is an exception:
   * [Static type checking] - Cannot assign value of type java.lang.String to variable of type
   * org.ysb33r.grolifant.api.exec.ResolvableExecutable
   * <grv87 2018-08-12>
   */
  @SuppressWarnings('UnnecessarySetter')
  PackerExecSpec(Project project, ExternalExecutable registry) {
    super(project, registry)
    setExecutable 'packer'
  }
}
