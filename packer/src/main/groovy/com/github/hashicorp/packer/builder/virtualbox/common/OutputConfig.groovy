package com.github.hashicorp.packer.builder.virtualbox.common

import com.fasterxml.jackson.annotation.JsonProperty
import org.fidata.packer.engine.types.base.InterpolableObject
import org.fidata.packer.engine.types.InterpolableString
import groovy.transform.CompileStatic
import org.gradle.api.tasks.OutputDirectory

@CompileStatic
class OutputConfig extends InterpolableObject {
  @JsonProperty('output_directory')
  @OutputDirectory
  InterpolableString outputDir
}