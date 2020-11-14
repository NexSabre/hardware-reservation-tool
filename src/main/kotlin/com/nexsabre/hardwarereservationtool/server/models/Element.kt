package com.nexsabre.hardwarereservationtool.server.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Element(val id: Int? = null,
                   val name: String?,
                   val address: String?,
                   @Contextual
                   val start: Long? = null,
                   @Contextual
                   val ends: Long? = null,
                   @Contextual
                   val enabled: Boolean = true,
                   @Contextual
                   val protected: Boolean = false
)

fun Machine.toElement() = Element(
        id = id.value,
        name = name,
        address = address,
        start = reservationStart,
        ends = reservationEnds,
        enabled = enabled,
        protected = protected
)