package com.nexsabre.hardwarereservationtool.server.requests

data class AddMachine(val name: String, val address: String, val enabled: Boolean = true)