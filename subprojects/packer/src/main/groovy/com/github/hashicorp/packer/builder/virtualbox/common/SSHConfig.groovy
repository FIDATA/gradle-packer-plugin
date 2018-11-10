package com.github.hashicorp.packer.builder.virtualbox.common

import com.github.hashicorp.packer.engine.annotations.Inline
import com.github.hashicorp.packer.engine.types.InterpolableObject
import com.github.hashicorp.packer.engine.types.InterpolableUnsignedInteger
import com.github.hashicorp.packer.helper.Communicator
import groovy.transform.AutoClone
import groovy.transform.AutoCloneStyle
import groovy.transform.CompileStatic
import org.gradle.api.tasks.Internal

@AutoClone(style = AutoCloneStyle.SIMPLE)
@CompileStatic
class SSHConfig extends InterpolableObject {
  @Inline
  Communicator comm

  @Internal
  InterpolableUnsignedInteger sshHostPortMin

  @Internal
  InterpolableUnsignedInteger sshHostPortMax

  @Internal
  InterpolableUnsignedInteger sshSkipNatMapping
}
