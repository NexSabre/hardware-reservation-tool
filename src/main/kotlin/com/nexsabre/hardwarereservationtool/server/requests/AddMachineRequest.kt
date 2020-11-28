package com.nexsabre.hardwarereservationtool.server.requests

data class AddMachineRequest(val name: String, val address: String, val enabled: Boolean = true)