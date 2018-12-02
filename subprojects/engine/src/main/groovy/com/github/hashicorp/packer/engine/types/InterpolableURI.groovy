package com.github.hashicorp.packer.engine.types

import com.fasterxml.jackson.annotation.JsonCreator
import com.github.hashicorp.packer.engine.annotations.ComputedInternal
import com.github.hashicorp.packer.template.Context
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

@CompileStatic
interface InterpolableURI<ThisInterface extends InterpolableURI<ThisInterface>> extends InterpolableValue<Object, URI, ThisInterface> {
  class ImmutableRaw<
    ThisInterface extends InterpolableURI<ThisInterface>,
    InterpolatedClass extends Interpolated<ThisInterface, AlreadyInterpolatedClass>,
    AlreadyInterpolatedClass extends AlreadyInterpolated<ThisInterface>
  > extends InterpolableValue.ImmutableRaw<Object, URI, InterpolableURI, InterpolatedClass, AlreadyInterpolatedClass> implements InterpolableURI<ThisInterface> {
    ImmutableRaw() {
      super()
    }

    @JsonCreator
    ImmutableRaw(URI raw) {
      super(raw)
    }

    @JsonCreator
    ImmutableRaw(SimpleInterpolableString raw) {
      super(raw)
    }

    protected static final URI doInterpolatePrimitive(Context context, URI raw) {
      raw
    }

    protected static final URI doInterpolatePrimitive(Context context, SimpleInterpolableString raw) {
      context.resolveUri raw.interpolate(context)
    }
  }

  class Raw<
    ThisInterface extends InterpolableURI<ThisInterface>,
    InterpolatedClass extends Interpolated<ThisInterface, AlreadyInterpolatedClass>,
    AlreadyInterpolatedClass extends AlreadyInterpolated<ThisInterface>
    > extends InterpolableValue.Raw<Object, URI, InterpolableURI, InterpolatedClass, AlreadyInterpolatedClass> implements InterpolableURI<ThisInterface> {
    Raw() {
      super()
    }

    @JsonCreator
    Raw(URI raw) {
      super(raw)
    }

    @JsonCreator
    Raw(SimpleInterpolableString raw) {
      super(raw)
    }

    protected static final URI doInterpolatePrimitive(Context context, URI raw) {
      raw
    }

    protected static final URI doInterpolatePrimitive(Context context, SimpleInterpolableString raw) {
      context.resolveUri raw.interpolate(context)
    }
  }

  @InheritConstructors
  class Interpolated<
    ThisInterface extends InterpolableURI<ThisInterface>,
    AlreadyInterpolatedClass extends AlreadyInterpolated<ThisInterface>
  > extends InterpolableValue.Interpolated<Object, URI, InterpolableURI, AlreadyInterpolatedClass> implements InterpolableURI<ThisInterface> {
    @ComputedInternal
    URI getFileURI() {
      interpolated?.scheme == 'file' ? interpolated : null
    }

    @ComputedInternal
    URI getNonFileURI() {
      interpolated?.scheme != 'file' ? interpolated : null
    }
  }

  @InheritConstructors
  class AlreadyInterpolated<
    ThisInterface extends InterpolableURI<ThisInterface>
  > extends InterpolableValue.AlreadyInterpolated<Object, URI, InterpolableURI> implements InterpolableURI<ThisInterface> {
    @ComputedInternal
    URI getFileURI() {
      interpolated?.scheme == 'file' ? interpolated : null
    }

    @ComputedInternal
    URI getNonFileURI() {
      interpolated?.scheme != 'file' ? interpolated : null
    }
  }
}
