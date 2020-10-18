package com.nexsabre.hardwarereservationtool.models

import org.joda.time.DateTime

data class Element(val id: Int? = null,
                   val name: String?,
                   val address: String?,
                   val start: DateTime? = null,
                   val ends: DateTime? = null)

fun Machine.toElement() = Element(
        id = id.value,
        name = name,
        address = address,
        start = reservationStart,
        ends = reservationEnds
)