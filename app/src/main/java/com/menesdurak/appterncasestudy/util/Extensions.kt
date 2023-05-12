package com.menesdurak.appterncasestudy.util

fun convertLengthToMinAndSec(trackLength: Int): String {
    val minute = trackLength / 60
    val second = trackLength % 60
    return "$minute:$second\""
}