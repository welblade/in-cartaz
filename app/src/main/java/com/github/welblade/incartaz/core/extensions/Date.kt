package com.github.welblade.incartaz.core.extensions

import java.text.SimpleDateFormat
import java.util.*
val locate =  Locale("pt", "BR")
fun Date.format(pattern: String = "dd/MM/yyyy"): String {
    return SimpleDateFormat(pattern, locate).format(this)
}
fun String.toDate(pattern: String = "yyyy-MM-dd"): Date? {
    return SimpleDateFormat(pattern).parse(this)
}