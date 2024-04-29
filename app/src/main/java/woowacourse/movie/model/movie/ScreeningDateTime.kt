package woowacourse.movie.model.movie

import java.io.Serializable

data class ScreeningDateTime(
    val date: String,
    val time: String,
) : Serializable
