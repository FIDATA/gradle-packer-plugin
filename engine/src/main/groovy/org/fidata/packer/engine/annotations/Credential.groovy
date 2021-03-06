package org.fidata.packer.engine.annotations

import groovy.transform.AnnotationCollector
import groovy.transform.CompileStatic
import org.gradle.api.tasks.Internal
import java.lang.annotation.Documented

/**
 * This annotation is used to indicate that the property specifies
 * credentials used to connect to remote servers,
 * and so is ignored for up-to-date detection
 */
@AnnotationCollector([Internal])
@Documented
@CompileStatic
@interface Credential {
}
