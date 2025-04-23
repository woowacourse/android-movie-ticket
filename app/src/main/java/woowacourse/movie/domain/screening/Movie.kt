package woowacourse.movie.domain.screening

import java.io.Serializable

data class Movie(
    val id: Int,
    val title: String,
    val runningTime: Int,
) : Serializable
