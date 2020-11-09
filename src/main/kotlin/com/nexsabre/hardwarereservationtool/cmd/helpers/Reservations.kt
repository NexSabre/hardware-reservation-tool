package com.nexsabre.hardwarereservationtool.cmd.helpers

import com.nexsabre.hardwarereservationtool.cmd.models.Hart
import com.nexsabre.hardwarereservationtool.server.models.Element
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun getAllReservations(): List<Element>? {
    val response = Hart().reservations() ?: return null
    return Json.decodeFromString<List<Element>>(response)
}