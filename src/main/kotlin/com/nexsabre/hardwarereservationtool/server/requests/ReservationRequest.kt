package com.nexsabre.hardwarereservationtool.server.requests

data class ReservationRequest(val id: Int, val start: Any?, val duration: Int = 0, val password: String?)