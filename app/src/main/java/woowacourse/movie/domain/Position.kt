package woowacourse.movie.domain

import java.io.Serializable

data class Position(
    val row: Int,
    val col: Int,
) : Serializable
