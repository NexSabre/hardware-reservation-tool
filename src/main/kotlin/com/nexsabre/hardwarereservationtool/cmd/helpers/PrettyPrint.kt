package com.nexsabre.hardwarereservationtool.cmd.helpers

import com.nexsabre.hardwarereservationtool.server.models.Element

fun List<Element>?.prettyPrint() {
    if (this == null || this.isEmpty()) {
        println("0. \t Nothing to display")

    } else {
        this.forEachIndexed { index, element ->
            println("${index + 1}. $element")
        }
    }
}

fun Element?.prettyPrint() {
    if (this == null) {
        println("0. \t Nothing to display")
    } else {
        println("1. \t $this")
    }
}
