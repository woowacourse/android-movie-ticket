package woowacourse.movie.model

import java.io.Serializable

data class Seat(
    val row: Char,
    val column: Int,
    val grade: Grade,
) : Serializable
