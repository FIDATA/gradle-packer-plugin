package com.github.hashicorp.packer.engine.types.base

import groovy.transform.CompileStatic
import com.github.hashicorp.packer.template.Context

@CompileStatic
interface InterpolableObject<ReadOnlyClass extends InterpolableObject> {
  /**
   *
   * @param context
   * @throws UnsupportedOperationException
   * @return
   */
  ReadOnlyClass interpolate(Context context)
}