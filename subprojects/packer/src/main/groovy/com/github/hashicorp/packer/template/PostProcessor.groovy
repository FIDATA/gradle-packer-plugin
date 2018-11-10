/*
 * PostProcessor class
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

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonUnwrapped
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.jsontype.NamedType
import com.github.hashicorp.packer.engine.annotations.Default
import com.github.hashicorp.packer.engine.exceptions.InvalidRawValueClass
import com.github.hashicorp.packer.engine.exceptions.ObjectAlreadyInterpolatedForBuilder
import com.github.hashicorp.packer.packer.Artifact
import groovy.transform.AutoClone
import groovy.transform.AutoCloneStyle
import groovy.transform.CompileStatic
import groovy.transform.CompileDynamic
import com.github.hashicorp.packer.engine.types.InterpolableObject
import com.github.hashicorp.packer.engine.types.InterpolableBoolean
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Nested

@AutoClone(style = AutoCloneStyle.SIMPLE)
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = 'type'
)
@CompileStatic
// REVIEWED
abstract class PostProcessor extends InterpolableObject {
  protected PostProcessor() {
  }

  @JsonUnwrapped
  @Internal
  // TODO
  OnlyExcept onlyExcept

  @Input
  String type

  @Internal
  @Default(value = 'false') // TODO
  InterpolableBoolean keepInputArtifacts

  @Override
  protected void doInterpolate() {
    keepInputArtifacts.interpolate context
  }

  final PostProcessor interpolateForBuilder(Context buildCtx) {
    if (context.buildName) {
      throw new ObjectAlreadyInterpolatedForBuilder()
    }
    // Stage 3
    if (/*onlyExcept == null ||*/ !onlyExcept?.skip(buildCtx.buildName)) {
      PostProcessor result = this.clone()
      result.interpolate buildCtx
      result
    } else {
      null
    }
  }

  /*
   * WORKAROUND:
   * Groovy bug https://issues.apache.org/jira/browse/GROOVY-7985.
   * Nested generics are not supported in static compile mode.
   * <grv87 2018-11-10>
   */
  @CompileDynamic
  final Tuple2<Tuple2<Artifact, Boolean>, List<Provider<Boolean>>> postProcess(Artifact priorArtifact) {
    if (!interpolated) {
      throw new IllegalStateException('') // TODO
    }
    // Stage 4
    Tuple2<Tuple2<Artifact, Boolean>, List<Provider<Boolean>>> result = doPostProcess(priorArtifact)
    new Tuple2(new Tuple2(result.first.first, result.first.second || keepInputArtifacts.interpolatedValue), result.second)
  }

  protected abstract Tuple2<Tuple2<Artifact, Boolean>, List<Provider<Boolean>>> doPostProcess(Artifact priorArtifact)

  private static final Map<String, Class<? extends PostProcessor>> SUBTYPES = [:]

  static void registerSubtype(String type, Class<? extends PostProcessor> clazz) {
    SUBTYPES.put type, clazz
    Template.MAPPER.registerSubtypes(new NamedType(clazz, type))
  }

  @AutoClone(style = AutoCloneStyle.SIMPLE)
  @CompileStatic
  static final class PostProcessorArrayDefinition extends InterpolableObject {
    static class ArrayClass extends ArrayList<PostProcessorDefinition> {
    }

    @JsonValue
    @Nested
    Object rawValue

    private PostProcessorArrayDefinition() {
    }

    @JsonCreator
    PostProcessorArrayDefinition(ArrayClass rawValue) {
      this.rawValue = rawValue
    }

    @JsonCreator
    PostProcessorArrayDefinition(PostProcessorDefinition rawValue) {
      this.rawValue = rawValue
    }

    @Override
    protected void doInterpolate() {
      if (ArrayClass.isInstance(rawValue)) {
        ((ArrayClass)rawValue).each { PostProcessorDefinition postProcessorDefinition -> postProcessorDefinition.interpolate context }
      } else if (PostProcessorDefinition.isInstance(rawValue)) {
        ((PostProcessorDefinition)rawValue).interpolate context
      } else {
        throw new InvalidRawValueClass(rawValue)
      }
    }

    final PostProcessorArrayDefinition interpolateForBuilder(Context buildCtx) {
      if (context.buildName) {
        throw new ObjectAlreadyInterpolatedForBuilder()
      }
      // Stage 3
      if (ArrayClass.isInstance(rawValue)) {
        ArrayClass result = (ArrayClass)(((ArrayClass)rawValue)*.interpolateForBuilder(buildCtx).findAll())
        if (result.size() > 0) {
          new PostProcessorArrayDefinition(result)
        } else {
          null
        }
      } else if (PostProcessorDefinition.isInstance(rawValue)) {
        PostProcessorDefinition result = ((PostProcessorDefinition)rawValue).interpolateForBuilder(buildCtx)
        if (result) {
          new PostProcessorArrayDefinition(result)
        } else {
          null
        }
      } else {
        throw new InvalidRawValueClass(rawValue)
      }
    }

    /*
     * WORKAROUND:
     * Groovy bug https://issues.apache.org/jira/browse/GROOVY-7985.
     * Nested generics are not supported in static compile mode.
     * <grv87 2018-11-10>
     */
    @CompileDynamic
    final Tuple2<Tuple2<List<Artifact>, Boolean>, List<Provider<Boolean>>>/*TODO: Groovy 2.5.0*/ postProcess(Artifact priorArtifact) {
      if (!interpolated) {
        throw new IllegalStateException('') // TODO
      }
      // Stage 4
      if (ArrayClass.isInstance(rawValue)) {
        List<Artifact> artifacts = []
        Boolean keep = true
        List<Provider<Boolean>> upToDateWhen = []
        Artifact _priorArtifact = priorArtifact
        ((ArrayClass)rawValue).eachWithIndex {  PostProcessorDefinition postProcessorDefinition, Integer i ->
          Tuple2<Tuple2<Artifact, Boolean>, List<Provider<Boolean>>> result = postProcessorDefinition.postProcess(_priorArtifact)
          _priorArtifact = result.first.first
          boolean _keep = result.first.second
          keep = keep && _keep
          if (_keep) {
            artifacts.add _priorArtifact
          } else {
            artifacts = [_priorArtifact]
          }
          upToDateWhen.addAll result.second
        }
        new Tuple2(new Tuple2(artifacts, keep), upToDateWhen)
      } else if (PostProcessorDefinition.isInstance(rawValue)) {
        Tuple2<Tuple2<Artifact, Boolean>, List<Provider<Boolean>>> result = ((PostProcessorDefinition)rawValue).postProcess(priorArtifact)
        new Tuple2(new Tuple2([result.first.first], result.first.second), result.second)
      } else {
        throw new InvalidRawValueClass(rawValue)
      }
    }
  }

  @AutoClone(style = AutoCloneStyle.SIMPLE)
  @CompileStatic
  static final class PostProcessorDefinition extends InterpolableObject {
    @JsonValue
    @Nested
    Object rawValue

    private PostProcessorDefinition() {
    }

    @JsonCreator
    PostProcessorDefinition(String rawValue) {
      this.rawValue = rawValue
    }

    @JsonCreator
    PostProcessorDefinition(PostProcessor rawValue) {
      this.rawValue = rawValue
    }

    @Override
    protected void doInterpolate() {
      if (PostProcessor.isInstance(rawValue)) {
        ((PostProcessor)rawValue).interpolate context
      } else if (!String.isInstance(rawValue)) {
        throw new InvalidRawValueClass(rawValue)
      }
    }

    final PostProcessorDefinition interpolateForBuilder(Context buildCtx) {
      if (context.buildName) {
        throw new ObjectAlreadyInterpolatedForBuilder()
      }
      // Stage 3
      if (PostProcessor.isInstance(rawValue)) {
        PostProcessor result = ((PostProcessor)rawValue).interpolateForBuilder(buildCtx)
        if (result) {
          new PostProcessorDefinition(result)
        } else {
          null
        }
      } else if (String.isInstance(rawValue)) {
        new PostProcessorDefinition(SUBTYPES[(String)rawValue].newInstance())
      } else {
        throw new InvalidRawValueClass(rawValue)
      }
    }

    /*
     * WORKAROUND:
     * Groovy bug https://issues.apache.org/jira/browse/GROOVY-7985.
     * Nested generics are not supported in static compile mode.
     * <grv87 2018-11-10>
     */
    @CompileDynamic
    final Tuple2<Tuple2<Artifact, Boolean>, List<Provider<Boolean>>> postProcess(Artifact priorArtifact) {
      if (!interpolated) {
        throw new IllegalStateException('') // TODO
      }
      // Stage 4
      if (PostProcessor.isInstance(rawValue)) {
        ((PostProcessor)rawValue).postProcess priorArtifact
      } else {
        throw new InvalidRawValueClass(rawValue)
      }
    }
  }
}
