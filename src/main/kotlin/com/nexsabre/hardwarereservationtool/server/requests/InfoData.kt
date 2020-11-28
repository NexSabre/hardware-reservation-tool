package com.nexsabre.hardwarereservationtool.server.requests

import kotlinx.serialization.Serializable

@Serializable
data class InfoData(val version: String, val status: String)