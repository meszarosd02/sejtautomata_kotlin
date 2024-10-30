package com.example.sejtautomata_kotlin.twodimensional

data class Rule (
    var comp: COMPARISON,
    var num: Int,
    var start: STARTSTATE,
    var result: Boolean
) {
    enum class COMPARISON{
        LESS,
        LESS_EQUAL,
        EQUAL,
        GREATER_EQUAL,
        GREATER
    }

    enum class STARTSTATE{
        ANY,
        ACTIVE,
        INACTIVE
    }
}