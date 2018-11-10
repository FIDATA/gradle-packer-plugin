package com.github.hashicorp.packer.builder.virtualbox.common

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.hashicorp.packer.engine.types.InterpolableObject
import com.github.hashicorp.packer.engine.types.InterpolableString
import groovy.transform.AutoClone
import groovy.transform.AutoCloneStyle
import groovy.transform.CompileStatic
import org.gradle.api.tasks.OutputDirectory

@AutoClone(style = AutoCloneStyle.SIMPLE)
@CompileStatic
class OutputConfig extends InterpolableObject {
  @JsonProperty('output_directory')
  @OutputDirectory
  InterpolableString outputDir
}