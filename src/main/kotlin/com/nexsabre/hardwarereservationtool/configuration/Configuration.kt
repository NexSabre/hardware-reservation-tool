package com.nexsabre.hardwarereservationtool.configuration

import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.Masked


data class Database(val url: String = "jdbc:sqlite:sqlite.test.db",
                    val driver: String = "org.sqlite.JDBC")

data class Rules(val reserveOnlyWithKey: Boolean = false,
                 val overwriteWithKey: Boolean = false,
                 var password: Masked)

data class Server(val port: Int,
                  val redirectUrl: String)

data class Config(val env: String,
                  val server: Server?,
                  val rules: Rules,
                  val database: Database = Database())

class Configuration {
    private val configurationPath: String = System.getenv("HART_CONFIG") ?: "/application-staging.yaml"

    fun all() = ConfigLoader().loadConfigOrThrow<Config>(this.configurationPath)

    fun database() = all().database
}