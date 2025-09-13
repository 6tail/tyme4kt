package com.tyme.util

fun Int.pad2() = toString().padStart(2, '0')
fun Int.pad4() = toString().padStart(4, '0')
fun Int.hexPad2() = toString(16).padStart(2, '0').uppercase()
