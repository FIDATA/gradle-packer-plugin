package com.github.hashicorp.packer.engine

import groovy.transform.CompileStatic

@CompileStatic
enum Mutability {
  IMMUTABLE,
  MUTABLE
}