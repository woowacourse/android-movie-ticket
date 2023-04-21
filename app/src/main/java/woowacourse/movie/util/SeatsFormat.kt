package woowacourse.movie.util

import woowacourse.movie.model.Location

fun List<String>.formatSeatsCombine(): String {
    val stringBuilder = StringBuilder()
    this.forEach { stringBuilder.append("$it ") }
    return stringBuilder.toString()
}

fun List<Location>.formatLocationToString(): List<String> =
    this.map { "${it.row}${it.number}" }
