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
package com.github.hashicorp.packer.template

import com.github.hashicorp.packer.engine.utils.ObjectMapperFacade
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.jsontype.NamedType
import com.github.hashicorp.packer.engine.annotations.ComputedInput
import com.github.hashicorp.packer.engine.utils.SubtypeRegistry
import com.github.hashicorp.packer.packer.Artifact
import groovy.transform.CompileStatic
import com.github.hashicorp.packer.engine.annotations.Inline
import com.github.hashicorp.packer.engine.types.InterpolableObject
import com.github.hashicorp.packer.engine.types.InterpolableString
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = 'type'
)
@CompileStatic
// REVIEWED
abstract class Builder extends InterpolableObject {
  protected Builder() {
  }

  @Inline
  BuilderHeader header

  @Override
  protected void doInterpolate() {
    header.interpolate context
  }

  final Tuple2<Artifact, List<Provider<Boolean>>> run() {
    if (!interpolated) {
      throw new IllegalStateException('') // TODO
    }
    // Stage 4
    doRun()
  }

  protected abstract Tuple2<Artifact, List<Provider<Boolean>>> doRun()

  static final class BuilderHeader extends InterpolableObject {
    @Internal
    InterpolableString name

    @Input
    String type

    @ComputedInput
    String getBuildName() {
      name?.interpolatedValue ?: type
    }

    @Override
    protected void doInterpolate() {
      name.interpolate context
    }
  }

  private static final class BuilderSubtypeRegistry extends SubtypeRegistry<Builder> { }
  protected static final SubtypeRegistry<Builder> SUBTYPE_REGISTRY = new BuilderSubtypeRegistry()
}
