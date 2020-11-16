package com.nexsabre.hardwarereservationtool.server.helpers

import com.nexsabre.hardwarereservationtool.server.configuration.Config
import com.sksamuel.hoplite.Masked

fun Config.mask() {
    this.rules.password = Masked("******")
}