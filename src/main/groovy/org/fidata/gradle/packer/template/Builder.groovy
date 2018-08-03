/*
 * Builder class
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
package org.fidata.gradle.packer.template

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.fidata.gradle.packer.template.builder.AmazonEbs
import org.fidata.gradle.packer.template.builder.VirtualBoxIso
import org.fidata.gradle.packer.template.builder.VirtualBoxOvf

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = 'type'
)
@JsonSubTypes([
  @JsonSubTypes.Type(name = 'amazon-ebs', value = AmazonEbs),
  @JsonSubTypes.Type(name = 'virtualbox-iso', value = VirtualBoxIso),
  @JsonSubTypes.Type(name = 'virtualbox-ovf', value = VirtualBoxOvf),
])
interface Builder {
  String name
  String type
}
