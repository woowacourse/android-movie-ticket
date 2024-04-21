package woowacourse.movie.presentation.screen.model

import java.io.Serializable

class MovieModel(
    val title: String,
    val posterResourceId: Int,
    val screeningDate: String,
    val runningTime: Int,
    val description: String,
):Serializable
