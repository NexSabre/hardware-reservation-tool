package com.nexsabre.hardwarereservationtool.configuration

import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.Masked

//
//@ConstructorBinding
//@ConfigurationProperties("rules")
//data class RulesConfig(var rule1: String, val rule2: String) {}

data class Rules(val reserveOnlyWithKey: Boolean = false, val overwriteWithKey: Boolean = false, val password: Masked)
data class Server(val port: Int, val redirectUrl: String)
data class Config(val env: String, val server: Server, val rules: Rules)

class Configuration {
    fun all() = ConfigLoader().loadConfigOrThrow<Config>("/application-staging.yaml")
}