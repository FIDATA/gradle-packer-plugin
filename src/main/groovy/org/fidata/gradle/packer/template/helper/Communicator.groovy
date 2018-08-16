package org.fidata.gradle.packer.template.helper

import groovy.transform.CompileStatic
import org.fidata.gradle.packer.template.Context
import org.fidata.gradle.packer.template.internal.InterpolableObject
import org.fidata.gradle.packer.template.types.InterpolableBoolean
import org.fidata.gradle.packer.template.types.InterpolableDuration
import org.fidata.gradle.packer.template.types.InterpolableInteger
import org.fidata.gradle.packer.template.types.InterpolableString
import org.gradle.api.tasks.Internal

@CompileStatic
class Communicator extends InterpolableObject {
  // SSH
  @Internal
  InterpolableString sshHost

  @Internal
  InterpolableInteger sshPort

  @Internal
  InterpolableString sshUsername

  @Internal
  InterpolableString sshPassword

  @Internal
  InterpolableString sshPrivateKey

  @Internal
  InterpolableBoolean sshPty

  @Internal
  InterpolableDuration sshTimeout

  @Internal
  InterpolableBoolean sshAgentAuth

  @Internal
  InterpolableBoolean sshDisableAgentForwarding

  @Internal
  InterpolableInteger sshHandshakeAttempts

  @Internal
  InterpolableString sshBastionHost

  @Internal
  InterpolableInteger sshBastionPort

  @Internal
  InterpolableBoolean sshBastionAgentAuth

  @Internal
  InterpolableString sshBastionUsername

  @Internal
  InterpolableString sshBastionPassword

  @Internal
  InterpolableString sshBastionPrivateKey

  @Internal
  InterpolableString sshFileTransferMethod

  @Internal
  InterpolableString sshProxyHost

  @Internal
  InterpolableInteger sshProxyPort

  @Internal
  InterpolableString sshProxyUsername

  @Internal
  InterpolableString sshProxyPassword

  @Internal
  InterpolableDuration sshKeepAliveInterval

  @Internal
  InterpolableDuration sshReadWriteTimeout

  // winrm
  @Internal
  InterpolableString winrmUser

  @Internal
  InterpolableString winrmPassword

  @Internal
  InterpolableString winrmHost

  @Internal
  InterpolableInteger winrmPort

  @Internal
  InterpolableDuration winrmTimeout

  @Internal
  InterpolableBoolean winrmUseSSL

  @Internal
  InterpolableBoolean winrmInsecure

  @Internal
  InterpolableBoolean winrmUseNTLM

  @Override
  protected void doInterpolate(Context ctx) {
    sshHost.interpolate ctx
    sshUsername.interpolate ctx
    sshPassword.interpolate ctx
    sshPrivateKey.interpolate ctx
    sshBastionHost.interpolate ctx
    sshBastionUsername.interpolate ctx
    sshBastionPassword.interpolate ctx
    sshBastionPrivateKey.interpolate ctx
    sshFileTransferMethod.interpolate ctx
    sshProxyHost.interpolate ctx
    sshProxyUsername.interpolate ctx
    sshProxyPassword.interpolate ctx
    winrmUser.interpolate ctx
    winrmPassword.interpolate ctx
    winrmHost.interpolate ctx
  }
}
