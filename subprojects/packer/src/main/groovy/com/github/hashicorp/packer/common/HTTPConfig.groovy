package com.github.hashicorp.packer.common

import com.github.hashicorp.packer.engine.types.InterpolableInputDirectory
import com.github.hashicorp.packer.engine.types.InterpolableObject
import com.github.hashicorp.packer.engine.types.InterpolableUnsignedInteger
import groovy.transform.AutoClone
import groovy.transform.AutoCloneStyle
import groovy.transform.CompileStatic
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Nested

@AutoClone(style = AutoCloneStyle.SIMPLE)
@CompileStatic
class HTTPConfig extends InterpolableObject {
  @Nested
  InterpolableInputDirectory httpDir

  @Internal
  InterpolableUnsignedInteger httpPortMin

  @Internal
  InterpolableUnsignedInteger httpPortMax
}