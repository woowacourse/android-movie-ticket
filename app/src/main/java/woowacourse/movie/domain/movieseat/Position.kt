package woowacourse.movie.domain.movieseat

import java.io.Serializable

data class Position(
    val row: Int,
    val column: Int,
) : Serializable
