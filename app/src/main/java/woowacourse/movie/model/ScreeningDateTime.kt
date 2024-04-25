package woowacourse.movie.model

import java.io.Serializable

data class ScreeningDateTime(
    val date: String,
    val time: String,
) : Serializable
