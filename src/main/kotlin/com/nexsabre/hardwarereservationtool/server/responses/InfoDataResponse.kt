package com.nexsabre.hardwarereservationtool.server.responses

import kotlinx.serialization.Serializable

@Serializable
data class InfoDataResponse(val version: String, val status: String, val message: String? = null)