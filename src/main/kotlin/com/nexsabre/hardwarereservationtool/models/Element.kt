package com.nexsabre.hardwarereservationtool.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.joda.time.DateTime

@Serializable
data class Element(val id: Int? = null,
                   val name: String?,
                   val address: String?,
                   @Contextual
                   val start: DateTime? = null,
                   @Contextual
                   val ends: DateTime? = null,
                   @Contextual
                   val enabled: Boolean = true
)

fun Machine.toElement() = Element(
        id = id.value,
        name = name,
        address = address,
        start = reservationStart,
        ends = reservationEnds,
        enabled = enabled
)