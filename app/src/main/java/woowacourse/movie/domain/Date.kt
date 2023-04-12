package woowacourse.movie.domain

import java.io.Serializable

data class Date(
    val year: Int,
    val month: Int,
    val day: Int
) : Serializable
