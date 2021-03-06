package org.fidata.packer.engine.types

import com.fasterxml.jackson.annotation.JsonCreator
import org.fidata.packer.engine.types.base.InterpolableValue
import org.fidata.packer.engine.types.base.SimpleInterpolableString
import com.github.hashicorp.packer.template.Context
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import groovy.transform.KnownImmutable

@CompileStatic
interface InterpolableInteger extends InterpolableValue<Object, Integer, InterpolableInteger> {
  @KnownImmutable
  final class ImmutableRaw extends InterpolableValue.ImmutableRaw<Object, Integer, InterpolableInteger, Interpolated, AlreadyInterpolated> implements InterpolableInteger {
    ImmutableRaw() {
      super()
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    ImmutableRaw(Integer raw) {
      super(raw)
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    /*
     * WORKAROUND:
     * Can't use argument of type SimpleInterpolableString
     * since Jackson doesn't work correctly with nested value classes
     * <grv87 2018-12-20>
     */
    ImmutableRaw(String raw) {
      super(new SimpleInterpolableString(raw))
    }

    protected static final Integer doInterpolatePrimitive(Context context, Integer raw) {
      raw
    }

    protected static final Integer doInterpolatePrimitive(Context context, SimpleInterpolableString raw) {
      raw.interpolate(context).toInteger()
    }
  }

  final class Raw extends InterpolableValue.Raw<Object, Integer, InterpolableInteger, Interpolated, AlreadyInterpolated> implements InterpolableInteger {
    Raw() {
      super()
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    Raw(Integer raw) {
      super(raw)
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    /*
     * WORKAROUND:
     * Can't use argument of type SimpleInterpolableString
     * since Jackson doesn't work correctly with nested value classes
     * <grv87 2018-12-20>
     */
    Raw(String raw) {
      super(new SimpleInterpolableString(raw))
    }

    protected static final Integer doInterpolatePrimitive(Context context, Integer raw) {
      raw
    }

    protected static final Integer doInterpolatePrimitive(Context context, SimpleInterpolableString raw) {
      raw.interpolate(context).toInteger()
    }
  }

  @InheritConstructors
  final class Interpolated extends InterpolableValue.Interpolated<Object, Integer, InterpolableInteger, AlreadyInterpolated> implements InterpolableInteger { }

  @KnownImmutable
  @InheritConstructors
  final class AlreadyInterpolated extends InterpolableValue.AlreadyInterpolated<Object, Integer, InterpolableInteger> implements InterpolableInteger { }
}
