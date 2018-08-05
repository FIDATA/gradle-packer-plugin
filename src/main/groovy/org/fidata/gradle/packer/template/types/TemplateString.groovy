package org.fidata.gradle.packer.template.types

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonValue
import groovy.transform.CompileStatic
import org.fidata.gradle.packer.template.Context
import org.fidata.gradle.packer.template.internal.TemplateObject
import org.gradle.api.tasks.Internal

@CompileStatic
class TemplateString extends TemplateObject {
  String value

  private String interpolatedValue

  String getInterpolatedValue() {
    interpolatedValue
  }

  @Override
  protected void doInterpolate(Context ctx) {
    interpolatedValue = ctx.interpolateString(value)
  }

  @Override
  boolean equals(Object obj) {
    this.class.isInstance(obj) && ((TemplateString)obj).interpolatedValue == interpolatedValue
  }

  private static final long serialVersionUID = 1L

  /*private void writeObject(ObjectOutputStream out) throws IOException {
    out.writeChars(interpolatedValue)
  }*/
  private Object writeReplace() throws ObjectStreamException {
    interpolatedValue
  }

  /* TOTEST TemplateString() {
  }*/
}