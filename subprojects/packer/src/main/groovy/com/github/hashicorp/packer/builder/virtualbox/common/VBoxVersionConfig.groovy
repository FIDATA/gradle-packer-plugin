package com.github.hashicorp.packer.builder.virtualbox.common

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.hashicorp.packer.engine.annotations.Default
import com.github.hashicorp.packer.engine.types.InterpolableObject
import com.github.hashicorp.packer.engine.types.InterpolableString
import groovy.transform.AutoClone
import groovy.transform.AutoCloneStyle
import groovy.transform.CompileStatic
import org.gradle.api.tasks.Input

@AutoClone(style = AutoCloneStyle.SIMPLE)
@CompileStatic
class VBoxVersionConfig extends InterpolableObject {
  @JsonProperty('virtualbox_version_file')
  @Input
  @Default(value = '.vbox_version') // in home dir !
  InterpolableString vboxVersionFile
}
