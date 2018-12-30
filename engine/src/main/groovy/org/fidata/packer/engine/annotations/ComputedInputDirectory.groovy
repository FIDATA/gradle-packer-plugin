package org.fidata.packer.engine.annotations

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.AnnotationCollector
import groovy.transform.CompileStatic
import org.gradle.api.tasks.InputDirectory

@AnnotationCollector([JsonIgnore, InputDirectory])
@CompileStatic
@interface ComputedInputDirectory {
}