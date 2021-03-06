package org.fidata.gradle.packer.tasks.arguments

import groovy.transform.CompileStatic
import org.fidata.packer.engine.annotations.ExtraProcessed
import org.gradle.api.file.RegularFile
import org.gradle.api.provider.Provider

@CompileStatic
trait PackerTemplateReadOnlyArgument extends PackerArgument {
  @ExtraProcessed
  final Provider<RegularFile> templateFile

  /*
   * WORKAROUND:
   * CodeNarc bug
   * Without getter we have error:
   * Call to super is not allowed in a trait
   * <grv87 2018-08-19>
   */
  @SuppressWarnings('UnnecessaryGetter')
  // TOTEST: @Internal
  @Override
  List<String> getCmdArgs() {
    List<String> cmdArgs = super.getCmdArgs()
    cmdArgs.add templateFile.get().toString()
    cmdArgs
  }
}
