package com.github.hashicorp.packer.engine.types

import groovy.transform.AutoClone
import groovy.transform.AutoCloneStyle
import groovy.transform.InheritConstructors
import groovy.transform.CompileStatic
import com.github.hashicorp.packer.engine.types.InterpolableValue

@AutoClone(style = AutoCloneStyle.SIMPLE)
@InheritConstructors
@CompileStatic
class InterpolableFile extends InterpolableValue<InterpolableString, File> {
  @Override
  protected File doInterpolatePrimitive() {
    rawValue.interpolate context
    context.interpolateFile(rawValue.interpolatedValue) // TODO
  }
}